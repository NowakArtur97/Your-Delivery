package com.nowakartur97.yourdelivery.publisher.web;

import com.nowakartur97.yourdelivery.publisher.message.Message;

public class MessageResponse {

    private final String messageId;

    private final Message message;

    public MessageResponse(String messageId, Message message) {
        this.messageId = messageId;
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public Message getMessage() {
        return message;
    }
}
