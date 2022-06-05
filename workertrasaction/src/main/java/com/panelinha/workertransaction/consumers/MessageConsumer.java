package com.panelinha.workertransaction.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.panelinha.workertransaction.constants.RabbitMQConstants;
import com.panelinha.workertransaction.constants.StatusEnum;
import com.panelinha.workertransaction.dto.TransactionDTO;
import com.panelinha.workertransaction.service.TransactionService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.Style;

@Component
public class MessageConsumer {
    @Autowired
    TransactionService transactionService;


    @RabbitListener(queues = RabbitMQConstants.PENDING, ackMode = "AUTO")
    private void consumerPending(String transactionDTO, Channel channel) throws Exception {
        try {
            TransactionDTO dto = DtoConverter(transactionDTO);
            transactionService.changeStatus(dto, StatusEnum.PROCESSING);

            channel.basicGet(RabbitMQConstants.PENDING, true);
        } catch (Exception e) {
            System.out.println("Error on pending consumer");
        }
    }

    @RabbitListener(queues = RabbitMQConstants.PROCESSING, ackMode = "AUTO")
    private void consumerProcess(String transactionDTO, Channel channel) throws Exception {
        TransactionDTO dto = null;
        try {
            dto = DtoConverter(transactionDTO);
            if (dto != null) {
                transactionService.transaction(dto);
            } else {
                System.out.println("Erro on converting json to dto");
            }
        } catch (Exception e) {
            System.out.println("Erro on processing transaction");
            transactionService.changeStatus(dto, StatusEnum.ERROR);
        } finally {
            channel.basicGet(RabbitMQConstants.PROCESSING, true);
        }

    }


    private TransactionDTO DtoConverter(String json) {
        final var objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, TransactionDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
