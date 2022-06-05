package com.panelinha.workertransaction.connections;

import com.panelinha.workertransaction.constants.RabbitMQConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {
    private static final String EXCHANGE_NAME = "amq.direct";
    private AmqpAdmin amqpAdmin = null;


    public RabbitMQConnection() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("birico");
        connectionFactory.setPassword("biricoseguro");

        AmqpAdmin admin = new RabbitAdmin(connectionFactory);
        this.amqpAdmin = admin;
    }

    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding relationship(Queue queue, DirectExchange exchange){
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }


    @PostConstruct
    private void add(){
        Queue q1 = this.queue(RabbitMQConstants.PENDING);
        Queue q2 = this.queue(RabbitMQConstants.PROCESSING);

        DirectExchange exchange = this.directExchange();

        Binding bindQ1 = this.relationship(q1, exchange);
        Binding bindQ2 = this.relationship(q2, exchange);

        this.amqpAdmin.declareQueue(q1);
        this.amqpAdmin.declareQueue(q2);

        this.amqpAdmin.declareExchange(exchange);

        this.amqpAdmin.declareBinding(bindQ1);
        this.amqpAdmin.declareBinding(bindQ2);
    }
}
