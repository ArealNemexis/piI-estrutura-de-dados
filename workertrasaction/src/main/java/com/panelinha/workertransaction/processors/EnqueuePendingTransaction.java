package com.panelinha.workertransaction.processors;


import com.panelinha.workertransaction.constants.RabbitMQConstants;
import com.panelinha.workertransaction.dto.TransactionDTO;
import com.panelinha.workertransaction.service.RabbitMQService;
import com.panelinha.workertransaction.service.TransactionService;
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
            rabbitMQService.sendMessage(RabbitMQConstants.PENDING, transactionDTO);
        });
    }
}
