package com.nowakartur97.yourdelivery.consumer.web;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponse {

    @JsonProperty("MessageId")
    private String messageId;

    @JsonProperty("Message")
    // TODO: Change type to: Message
    private String message;

    public MessageResponse(String messageId, String message) {
        this.messageId = messageId;
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "MessageResponse{messageId=" + messageId + ",message=" + message + "}";
    }
}
