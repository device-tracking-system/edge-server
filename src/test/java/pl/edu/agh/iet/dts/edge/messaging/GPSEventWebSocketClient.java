package pl.edu.agh.iet.dts.edge.messaging;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import pl.edu.agh.iet.dts.edge.messaging.format.GPSEvent;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
                final double[][] fakeEvents = new double[][]{
                        new double[]{49.987494d, 20.061944d},
                        new double[]{49.994200d, 20.041984d},
                        new double[]{50.006003d, 20.038044d},
                        new double[]{50.008518d, 20.056992d},
                        new double[]{50.017520d, 20.061804d},
                        new double[]{50.037270d, 20.064497d},
                        new double[]{50.051386d, 20.073123d},
                        new double[]{50.067904d, 20.067516d},
                        new double[]{50.071499d, 20.037987d},
                        new double[]{50.077975d, 20.029608d},
                        new double[]{50.077975d, 20.029608d},
                        new double[]{50.087087d, 20.007319d},
                        new double[]{50.087402d, 19.993886d},
                        new double[]{50.085512d, 19.976330d},
                        new double[]{50.085169d, 19.962193d},
                        new double[]{50.085786d, 19.954282d},
                        new double[]{50.078318d, 19.948952d},
                        new double[]{50.073291d, 19.941470d},
                        new double[]{50.072654d, 19.931310d},
                        new double[]{50.069239d, 19.926313d},
                        new double[]{50.066039d, 19.923977d},
                        new double[]{50.067619d, 19.913246d}};
                final Random randomGenerator = ThreadLocalRandom.current();

                for (double[] coordinates : fakeEvents) {
                    final GPSEvent event =
                            new GPSEvent("test", coordinates[0], coordinates[1], Instant.now().getEpochSecond());
                    session.send("/events", event);

                    try {
                        Thread.sleep((10 + randomGenerator.nextInt(5) - 2) * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };

        socketClient.connect("ws://localhost/events", sessionHandler);
        Thread.sleep(1000);
    }

}
