package com.nowakartur97.yourdelivery.consumer.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.nowakartur97.yourdelivery.consumer.message.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<MessageResponses> sendMessage() throws JsonProcessingException {
        return ResponseEntity.ok(messageService.receiveMessages());
    }
}
