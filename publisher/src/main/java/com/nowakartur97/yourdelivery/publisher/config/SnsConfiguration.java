package com.nowakartur97.yourdelivery.publisher.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

import java.net.URI;

@Configuration
public class SnsConfiguration {

    @Value("${your-delivery.aws.access-key}")
    private String accessKey;

    @Value("${your-delivery.aws.secret-key}")
    private String secretKey;

    @Value("${your-delivery.aws.region}")
    private String region;

    @Value("${your-delivery.aws.sns.endpoint:#{null}}")
    private String endpoint;

    @Bean
    @ConditionalOnProperty(name="spring.profiles.active", havingValue="docker")
    public SnsClient dockerSnsClient() {
        return SnsClient.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }

    @Bean
    @ConditionalOnProperty(name="spring.profiles.active", havingValue="local-aws")
    public SnsClient localAwsSnsClient() {
        return createDefaultSnsClient();
    }

    @Bean
    @ConditionalOnProperty(name="spring.profiles.active", havingValue="default")
    public SnsClient awsSnsClient() {
        return createDefaultSnsClient();
    }

    private SnsClient createDefaultSnsClient() {
        return SnsClient.builder()
                .region(Region.of(region))
                .build();
    }
}
