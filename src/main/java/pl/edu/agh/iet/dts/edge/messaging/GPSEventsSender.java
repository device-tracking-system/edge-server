package pl.edu.agh.iet.dts.edge.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.edu.agh.iet.dts.edge.messaging.format.GPSEvent;

/**
 * @author Bartłomiej Grochal
 */
@Service
public class GPSEventsSender {

    private final RabbitTemplate rabbitTemplate;
    private final String queueName;


    public GPSEventsSender(final RabbitTemplate rabbitTemplate,
                           @Value("${messaging.gpsEventsSender.queueName}") final String queueName) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }

    public void sendEvent(final GPSEvent event) {
        rabbitTemplate.convertAndSend(queueName, event);
    }

}
