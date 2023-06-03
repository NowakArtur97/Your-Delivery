package com.nowakartur97.yourdelivery.consumer.web;

import java.util.List;

public class MessageResponses {

    private final List<MessageResponse> messages;

    public MessageResponses(List<MessageResponse> messages) {
        this.messages = messages;
    }

    public List<MessageResponse> getMessages() {
        return messages;
    }
}
