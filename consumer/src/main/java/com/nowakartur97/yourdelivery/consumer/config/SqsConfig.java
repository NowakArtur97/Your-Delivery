package com.nowakartur97.yourdelivery.consumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;

@Configuration
public class SqsConfig {

    @Value("${your-delivery.aws.access-key}")
    private String accessKey;

    @Value("${your-delivery.aws.secret-key}")
    private String secretKey;

    @Value("${your-delivery.aws.region}")
    private String region;

    @Value("${your-delivery.aws.sqs.endpoint:#{null}}")
    private String endpoint;

    @Bean
    @ConditionalOnProperty(name="spring.profiles.active", havingValue="docker")
    public SqsClient dockerSqsClient() {
        return SqsClient.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }

    @Bean
    @ConditionalOnProperty(name="spring.profiles.active", havingValue="local-aws")
    public SqsClient localAwsSqsClient() {
        return createDefaultAwsSqsClient();
    }

    @Bean
    @ConditionalOnProperty(name="spring.profiles.active", havingValue="default")
    public SqsClient awsSqsClient() {
        return createDefaultAwsSqsClient();
    }

    private SqsClient createDefaultAwsSqsClient() {
        return SqsClient.builder()
                .region(Region.of(region))
                .build();
    }
}
