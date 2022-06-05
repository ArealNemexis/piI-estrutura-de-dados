package com.panelinha.transaction_scheduler.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panelinha.transaction_scheduler.constants.RabbitMQConstants;
import com.panelinha.transaction_scheduler.dto.TransactionDTO;
import com.panelinha.transaction_scheduler.service.RabbitMQService;
import com.panelinha.transaction_scheduler.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProcessTransaction {
    @Autowired
    TransactionService transactionService;

    @Autowired
    private RabbitMQService rabbitMQService;

    @Scheduled(fixedRate = 30 * 1000)
    public void Process() {
        List<TransactionDTO> transactionDTOList = transactionService.getProcessingTransactions();
        final var objectMapper = new ObjectMapper();

        transactionDTOList.forEach(transactionDTO -> {
            try {
                var json = objectMapper.writeValueAsString(transactionDTO);
                rabbitMQService.sendMessage(RabbitMQConstants.PROCESSING, json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}