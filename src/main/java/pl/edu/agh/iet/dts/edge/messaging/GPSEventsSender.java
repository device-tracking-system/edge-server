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
    private final String exchangeName;
    private final String bindingName;


    public GPSEventsSender(final RabbitTemplate rabbitTemplate,
                           @Value("${messaging.gpsEventsSender.exchangeName}") final String exchangeName,
                           @Value("${messaging.gpsEventsSender.bindingName}") final String bindingName) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.bindingName = bindingName;
    }

    public void sendEvent(final GPSEvent event) {
        rabbitTemplate.convertAndSend(exchangeName, bindingName, event);
    }

}
