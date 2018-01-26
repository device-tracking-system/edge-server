# Devices Tracking System - Edge Server
The Edge Server is a common entry point for all clients to use the Devices Tracking System.

## Project status
[![Build Status](https://travis-ci.org/device-tracking-system/edge-server.svg?branch=master)](https://travis-ci.org/device-tracking-system/edge-server)
[![codebeat badge](https://codebeat.co/badges/89051553-04f3-4b91-ab53-293eab16ec85)](https://codebeat.co/projects/github-com-device-tracking-system-edge-server-master)

## Prerequisites
You need to have the following tools installed and configured:
  - Java SE 1.8+
  - Maven 3.0+
  - RabbitMQ Server 3.0+

## Installation and Commissioning
In order to run the edge server, follow these steps:
  1. Add two environment variables: `GOOGLE_CLIENT_ID` and `GOOGLE_CLIENT_SECRET` containing
     the Client ID and Client Secret keys obtained by setting up the project integration with
     the Google OAuth2 on the [Google Developers Console](https://console.developers.google.com) 
     site.
  2. Run the [Configuration Server](https://github.com/device-tracking-system/configuration-server).
  3. Run the [Service Discovery](https://github.com/device-tracking-system/service-discovery).
  4. Run all the underlying services covered by this gateway service (including their dependencies such as MongoDB and
  RabbitMQ).
  5. Clone the latest production version of this repository from the `master` branch.
  6. Navigate to the cloned repository and install all dependencies by typing:
```
mvn install
``` 
  7. Run the built `*.jar` file passing the location of configuration files by typing:
```
java -jar target/edge-server-1.0-SNAPSHOT.jar --spring.config.location=classpath:pl/edu/agh/iet/dts/edge/
```

## Building the Docker image
When the `*.jar` file is successfully built, a Docker image for the production environment may be created by applying
following steps:
  1. Enter the root directory of this repository.
  2. Build the Docker image by typing:
```
docker build . -t edge-server
```
  3. In order to run the image, type:
```
docker run -p 80:80 -p 44361:44321 -p 44363:44323 -e GOOGLE_CLIENT_ID=[Google OAuth2 Client ID] -e GOOGLE_CLIENT_SECRET=[Google OAuth2 Client Secret] -e CONFIGURATION_SERVER_IP=[CONFIGURATION SERVER HOST IP ADDRESS] -e EUREKA_IP=[EUREKA HOST IP ADDRESS] -e ZIPKIN_IP=[ZIPKIN HOST IP ADDRESS] -e RABBITMQ_IP=[RABBITMQ HOST IP ADDRESS] -t edge-server
```
Please note that this docker container uses the Performance Co-Pilot (PCP) tool to gather data for system monitoring
metrics. These values are accessed via the `44361` and `44363` ports. In order to visualize performance of this
microservice, please enter the `[CONTAINER IP ADDRESS]:44363` value in the `Hostname` field placed in the Netflix Vector
dashboard.


## Testing
In order to test the application locally, run the built `*.jar` file by typing:
```
java -jar target/edge-server-1.0-SNAPSHOT.jar --spring.profiles.active=test --spring.config.location=classpath:pl/edu/agh/iet/dts/edge/
```
and then execute specific tests.

### Mocking WebSocket data
In order to send fake positions data to the WebSocket, use the `pl.edu.agh.iet.dts.edge.messaging.GPSEventWebSocketClient`
class placed under the `src/test/java` directory. At its startup, this class takes two parameters: address of the target 
WebSocket (given by: `ws://[EDGE SERVER HOST IP ADDRESS]/events`) and ID of a client whose data are being pushed.

## Debugging
In order to turn on debug logs for classes located in the `pl.edu.agh.iet.dts.*` package within this repository, please 
activate the `debug`  profile by setting the `--spring.profiles.active=[OTHER PROFILES],debug` flag and adding the 
`--debug` flag.
