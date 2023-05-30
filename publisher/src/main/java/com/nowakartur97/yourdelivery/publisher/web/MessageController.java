package com.nowakartur97.yourdelivery.publisher.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nowakartur97.yourdelivery.publisher.message.Message;
import com.nowakartur97.yourdelivery.publisher.message.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody Message message) throws JsonProcessingException {
        logger.info("Message received: [" + message + "]");
        return ResponseEntity.ok(messageService.sendMessage(message));
    }
}
