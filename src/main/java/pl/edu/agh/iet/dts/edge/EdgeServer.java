package pl.edu.agh.iet.dts.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Bartłomiej Grochal
 */
@SpringBootApplication
@EnableEurekaClient
public class EdgeServer {

    public static void main(String[] args) {
        SpringApplication.run(EdgeServer.class, args);
    }

}
