package pl.edu.agh.iet.dts.edge.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import pl.edu.agh.iet.dts.edge.messaging.format.GPSEvent;

/**
 * @author Bartłomiej Grochal
 */
@Controller
public class GPSEventController {

    @MessageMapping("/events")
    public void collectGPSEvents(GPSEvent event) {
        System.out.println("Event received: " + event);
    }

}
