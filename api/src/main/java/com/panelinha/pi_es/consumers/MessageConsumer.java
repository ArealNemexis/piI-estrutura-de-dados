package com.panelinha.pi_es.consumers;

import com.panelinha.pi_es.constants.RabbitMQConstants;
import com.panelinha.pi_es.dto.MessageDTO;
import com.panelinha.pi_es.dto.TransactionDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

//    @RabbitListener(queues = RabbitMQConstants.Q1)
//    private void consumer(MessageDTO messageDTO){
//        System.out.println(messageDTO.message);
//        System.out.println(messageDTO.code);
//
//    }

    @RabbitListener(queues = RabbitMQConstants.Q1)
    private void consumerPending(TransactionDTO transactionDTO){
        System.out.println(transactionDTO.getTransactionValue());
    }
}
