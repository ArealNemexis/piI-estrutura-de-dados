package com.panelinha.transaction_scheduler.processors;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panelinha.transaction_scheduler.constants.RabbitMQConstants;
import com.panelinha.transaction_scheduler.constants.StatusEnum;
import com.panelinha.transaction_scheduler.dto.TransactionDTO;
import com.panelinha.transaction_scheduler.service.RabbitMQService;
import com.panelinha.transaction_scheduler.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnqueuePendingTransaction {
    @Autowired
    TransactionService transactionService;

    @Autowired
    private RabbitMQService rabbitMQService;

    @Scheduled(fixedRate = 30 * 1000)
    public void Enqueue() {
        List<TransactionDTO> transactionDTOList = transactionService.getPendingTransactions();
        final var objectMapper = new ObjectMapper();

        transactionDTOList.forEach(transactionDTO -> {
            try {
                var json = objectMapper.writeValueAsString(transactionDTO);
                System.out.println("Mudando pra enqueued");
                transactionService.changeStatus(transactionDTO, StatusEnum.ENQUEUED);
                rabbitMQService.sendMessage(RabbitMQConstants.PENDING, json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
