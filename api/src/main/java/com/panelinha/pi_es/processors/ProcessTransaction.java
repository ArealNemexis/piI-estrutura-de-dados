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
public class ProcessTransaction {
    @Autowired
    TransactionService transactionService;

    @Autowired
    private RabbitMQService rabbitMQService;

    @Scheduled(fixedRate = 30*1000)
    public void Process(){
        List<TransactionDTO> transactionDTOList = transactionService.getProcessingTransactions();

        transactionDTOList.forEach(transactionDTO -> {
            rabbitMQService.sendMessage(RabbitMQConstants.PROCESSING, transactionDTO);
        });
    }
}
