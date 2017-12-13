package pl.edu.agh.iet.dts.edge.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

/**
 * @author Bartłomiej Grochal
 */
@Configuration
public class GPSEventSenderConfiguration {

    @Bean
    public Queue Queue(@Value("${messaging.gpsEventsSender.queueName}") final String queueName) {
        return new Queue(queueName, false);
    }

    @Bean
    public TopicExchange TopicExchange(@Value("${messaging.gpsEventsSender.exchangeName}") final String exchangeName) {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding AggregationTaskTopicExchangeToQueueBinding(final Queue queue, final TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with(queue.getName());
    }

    @Bean
    public MessageConverter JSONMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate RabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(JSONMessageConverter());
        return rabbitTemplate;
    }

}
