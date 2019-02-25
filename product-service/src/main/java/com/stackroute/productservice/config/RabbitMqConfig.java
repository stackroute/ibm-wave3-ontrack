package com.stackroute.productservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${jsa.rabbitmq.queue}")
    String queueName1;

    @Value("${jsa.rabbitmq.queue}")
    String queueName2;

    @Value("${jsa.rabbitmq.exchange}")
    String exchange;

    @Value("${jsa.rabbitmq.routingkey}")
    private String routingkey1;

    @Value("${jsa.rabbitmq.routingkey}")
    private String routingkey2;

    @Bean
    Queue autoDeleteQueue1() {
        return new Queue(queueName1, true);
    }
    @Bean
    Queue autoDeleteQueue2() {
        return new Queue(queueName1, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
   public Binding bindingrecommendation(Queue autoDeleteQueue1, DirectExchange exchange) {
        return BindingBuilder.bind(autoDeleteQueue1).to(exchange).with(routingkey1);
    }

    @Bean
    public Binding bindingsearch(DirectExchange exchange,
                             Queue autoDeleteQueue2) {
        return BindingBuilder.bind(autoDeleteQueue2).to(exchange).with(routingkey2);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
