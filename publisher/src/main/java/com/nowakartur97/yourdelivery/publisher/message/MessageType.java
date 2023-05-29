package com.nowakartur97.yourdelivery.publisher.message;

public enum MessageType {

    NOTIFICATION("notification"), INVENTORY("inventory"), SHIPMENT("shipment");

    private final String value;

    MessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
