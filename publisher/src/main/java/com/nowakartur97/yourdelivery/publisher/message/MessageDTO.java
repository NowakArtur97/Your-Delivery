package com.nowakartur97.yourdelivery.publisher.message;

public class MessageDTO {

    private MessageType type;

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MessageDTO{type=" + type + "}";
    }
}
