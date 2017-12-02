package pl.edu.agh.iet.dts.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Bartłomiej Grochal
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class EdgeServer {

    public static void main(String[] args) {
        SpringApplication.run(EdgeServer.class, args);
    }

}
