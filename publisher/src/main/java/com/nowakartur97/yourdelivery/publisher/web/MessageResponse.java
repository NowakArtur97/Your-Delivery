package com.nowakartur97.yourdelivery.publisher.web;

import com.nowakartur97.yourdelivery.publisher.message.MessageRequest;

public class MessageResponse {

    private final String messageId;

    private final MessageRequest message;

    public MessageResponse(String messageId, MessageRequest message) {
        this.messageId = messageId;
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public MessageRequest getMessage() {
        return message;
    }
}
