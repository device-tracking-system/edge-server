# Devices Tracking System - Edge Server
The Edge Server is a common entry point for all clients to use the Devices Tracking System.

## Project status
[![Build Status](https://travis-ci.org/device-tracking-system/edge-server.svg?branch=master)](https://travis-ci.org/device-tracking-system/edge-server)
[![codebeat badge](https://codebeat.co/badges/89051553-04f3-4b91-ab53-293eab16ec85)](https://codebeat.co/projects/github-com-device-tracking-system-edge-server-master)

## Prerequisites
You need to have the following tools installed and configured:
  - Java SE 1.8+
  - Maven 3.0+

## Installation and Commissioning
In order to run the edge server, follow these steps:
  1. Add two environment variables: `GOOGLE_CLIENT_ID` and `GOOGLE_CLIENT_SECRET` containing
     the Client ID and Client Secret keys obtained by setting up the project integration with
     the Google OAuth2 on the [Google Developers Console](https://console.developers.google.com) 
     site.
  2. Run the [Configuration Server](https://github.com/device-tracking-system/configuration-server).
  3. Run the [Service Discovery](https://github.com/device-tracking-system/service-discovery).
  4. Run all the underlying services covered by this gateway service.
  5. Clone the latest production version of this repository from the `master` branch.
  6. Navigate to the cloned repository and install all dependencies by typing:
```
mvn install
``` 
  7. Run the built `*.jar` file passing the location of configuration files by typing:
```
java -jar target/edge-server-1.0-SNAPSHOT.jar --spring.config.location=classpath:pl/edu/agh/iet/dts/edge/
```
