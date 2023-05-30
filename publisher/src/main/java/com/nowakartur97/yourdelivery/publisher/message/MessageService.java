package com.nowakartur97.yourdelivery.publisher.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowakartur97.yourdelivery.publisher.web.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Service
public class MessageService {

    private final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final String topicArn;
    private final SnsClient snsClient;
    private final ObjectMapper objectMapper;

    public MessageService(@Value("${your-delivery.aws.sns.topic-arn}") String topicArn, SnsClient snsClient, ObjectMapper objectMapper) {
        this.topicArn = topicArn;
        this.snsClient = snsClient;
        this.objectMapper = objectMapper;
    }

    public MessageResponse sendMessage(Message message) throws JsonProcessingException {
        PublishRequest request = PublishRequest.builder()
                .topicArn(topicArn)
                .message(objectMapper.writeValueAsString(message))
                .build();
        PublishResponse response = snsClient.publish(request);
        logger.info("Message sent (messageId: [" + response.messageId() + "]");
        return new MessageResponse(response.messageId(), message);
    }
}
