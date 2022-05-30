package com.panelinha.pi_es.controller;

import com.panelinha.pi_es.constants.RabbitMQConstants;
import com.panelinha.pi_es.dto.MessageDTO;
import com.panelinha.pi_es.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "message")
public class MessageController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PostMapping
    public ResponseEntity sendMessage(@RequestBody MessageDTO messageDTO) {
        this.rabbitMQService.sendMessage(RabbitMQConstants.PENDING, messageDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
