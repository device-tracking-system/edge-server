package pl.edu.agh.iet.dts.edge.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bartłomiej Grochal
 */
@Configuration
public class GPSEventSenderConfiguration {

    @Bean
    public Queue Queue(@Value("${messaging.gpsEventsSender.queueName}") final String queueName) {
        return new Queue(queueName, true, false, false);
    }

    @Bean
    public DirectExchange DirectExchange(@Value("${messaging.gpsEventsSender.exchangeName}") final String exchangeName) {
        return new DirectExchange(exchangeName, true, false);
    }

    @Bean
    public Binding AggregationTaskDirectExchangeToQueueBinding(final Queue queue, final DirectExchange exchange,
                                                               @Value("${messaging.gpsEventsSender.bindingName}") final String bindingName) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(bindingName);
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
