package com.panelinha.pi_es.consumers;

import com.panelinha.pi_es.constants.RabbitMQConstants;
import com.panelinha.pi_es.constants.StatusEnum;
import com.panelinha.pi_es.dto.TransactionDTO;
import com.panelinha.pi_es.service.TransactionService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    @Autowired
    TransactionService transactionService;

//    @RabbitListener(queues = RabbitMQConstants.Q1)
//    private void consumer(MessageDTO messageDTO){
//        System.out.println(messageDTO.message);
//        System.out.println(messageDTO.code);
//
//    }

    @RabbitListener(queues = RabbitMQConstants.PENDING,ackMode = "AUTO")
    private void consumerPending(TransactionDTO transactionDTO, Channel channel) throws Exception {
        transactionService.changeStatus(transactionDTO, StatusEnum.PROCESSING);
        channel.basicGet(RabbitMQConstants.PENDING, true);
    }

    @RabbitListener(queues = RabbitMQConstants.PROCESSING,ackMode = "AUTO")
    private void consumerProcess(TransactionDTO transactionDTO, Channel channel) throws Exception {
        try{
            transactionService.transaction(transactionDTO);

        } catch (Exception e) {
            System.out.println("Deu ruim, moio a favela");
            transactionService.changeStatus(transactionDTO, StatusEnum.ERROR);
        }
        finally {
            channel.basicGet(RabbitMQConstants.PROCESSING, true);

        }

    }
}
