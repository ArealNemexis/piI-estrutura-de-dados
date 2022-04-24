package com.panelinha.pi_es.processors;


import com.panelinha.pi_es.constants.RabbitMQConstants;
import com.panelinha.pi_es.dto.TransactionDTO;
import com.panelinha.pi_es.service.RabbitMQService;
import com.panelinha.pi_es.service.TransactionService;
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

    @Scheduled(fixedRate = 30*1000)
    public void Enqueue() {
        List<TransactionDTO> transactionDTOList = transactionService.getPendingTransactions();

        transactionDTOList.forEach(transactionDTO -> {
            rabbitMQService.sendMessage(RabbitMQConstants.Q1, transactionDTO);
        });
    }
}
