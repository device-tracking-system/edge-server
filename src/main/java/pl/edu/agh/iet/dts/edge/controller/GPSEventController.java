package pl.edu.agh.iet.dts.edge.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import pl.edu.agh.iet.dts.edge.messaging.GPSEventsSender;
import pl.edu.agh.iet.dts.edge.messaging.format.GPSEvent;

/**
 * @author Bartłomiej Grochal
 */
@Controller
public class GPSEventController {

    @Autowired private GPSEventsSender eventsSender;


    @MessageMapping("/events")
    public void collectGPSEvents(final GPSEvent event) {
        eventsSender.sendEvent(event);

        LoggerFactory.getLogger(GPSEventController.class)
                .debug(String.format("[WS /events] %s", event));
    }

}
