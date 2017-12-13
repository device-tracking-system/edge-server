package pl.edu.agh.iet.dts.edge.messaging.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author Bartłomiej Grochal
 */
@Configuration
@EnableWebSocketMessageBroker
public class GPSEventWebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/events");
        registry.addEndpoint("/events").withSockJS();
    }

}