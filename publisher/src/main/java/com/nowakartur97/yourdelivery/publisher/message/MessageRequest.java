package com.nowakartur97.yourdelivery.publisher.message;

public class MessageRequest {

    private final String type;

    public MessageRequest(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
