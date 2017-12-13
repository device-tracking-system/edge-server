package pl.edu.agh.iet.dts.edge.messaging;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import pl.edu.agh.iet.dts.edge.messaging.format.GPSEvent;

/**
 * @author Bartłomiej Grochal
 */
public class GPSEventWebSocketClient {

    public static void main(String[] args) throws InterruptedException {
        final WebSocketStompClient socketClient = new WebSocketStompClient(new StandardWebSocketClient());
        socketClient.setMessageConverter(new MappingJackson2MessageConverter());

        final StompSessionHandler sessionHandler = new StompSessionHandlerAdapter() {

            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                session.send("/events", new GPSEvent("test", 1.0d, 2.0d, 123L));
            }

        };

        socketClient.connect("ws://localhost/events", sessionHandler);
        Thread.sleep(1000);
    }

}
