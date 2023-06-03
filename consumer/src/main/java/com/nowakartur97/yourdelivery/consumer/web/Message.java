package com.nowakartur97.yourdelivery.consumer.web;

public class Message {

    private MessageType type;

    public Message(MessageType type) {
        this.type = type;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String toString() {
        return "Message{type=" + type + "}";
    }
}
