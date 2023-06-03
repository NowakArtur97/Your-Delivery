package com.nowakartur97.yourdelivery.consumer.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nowakartur97.yourdelivery.consumer.web.MessageResponse;
import com.nowakartur97.yourdelivery.consumer.web.MessageResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final String queueUrl;
    private final SqsClient sqsClient;
    private final ObjectMapper objectMapper;

    public MessageService(@Value("${your-delivery.aws.sqs.queue-url}") String queueUrl, SqsClient sqsClient, ObjectMapper objectMapper) {
        this.queueUrl = queueUrl;
        this.sqsClient = sqsClient;
        this.objectMapper = objectMapper;
    }

    public MessageResponses receiveMessages() throws JsonProcessingException {
        ReceiveMessageRequest request = createRequest();
        ReceiveMessageResponse response = sqsClient.receiveMessage(request);
        List<Message> sqsMessages = response.messages();
        List<MessageResponse> messages = new ArrayList<>();

        for (Message message : sqsMessages) {
            MessageResponse messageResponse = objectMapper.readValue(message.body(), MessageResponse.class);
            messages.add(messageResponse);
            logger.info("Message received: [" + messageResponse + "]");
            deleteMessage(queueUrl, message.receiptHandle());
        }

        return new MessageResponses(messages);
    }

    private void deleteMessage(String queueUrl, String receiptHandle) {
        DeleteMessageRequest request = DeleteMessageRequest.builder()
                .queueUrl(queueUrl)
                .receiptHandle(receiptHandle)
                .build();
        sqsClient.deleteMessage(request);
    }

    private ReceiveMessageRequest createRequest() {
        return ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(10)
                .waitTimeSeconds(10)
                .build();
    }
}
