# knowledgeboost-challenge
Repository for Final Code Challenge

This project contains a microservice which has 2 main operations:

**1. Get weather by city**

**2. Get weather by coordinates (latitude and longitude)**

Both endpoints retrieve the weather information of today per city.

## Getting Started ##

### System Requirements ###

| Tecnology | Download                                            | 
|-----------|-----------------------------------------------------|
| Java 17   | https://www.oracle.com/java/technologies/downloads/ |
| MySQL     | https://dev.mysql.com/downloads/mysql/              |
| DBMS      | https://dev.mysql.com/downloads/workbench/          |
| Maven     | https://maven.apache.org/download.cgi.              |
| Git       | https://git-scm.com/downloads.                      |


### Steps to install ###
1. You must install the above software.
2. When you have MySQL installed you have to enter to the service and create a database. The suggested name is weather-information. It can be changed as desired in the properties file.
2. Download the source code by cloning it using Git: git clone git@github.com:mpluma/knowledgeboost-challenge.git
2. Enter to the project folder and stay in the root dir, and through the command line and run the next command: <sup> mvn spring-boot:run </sup>
3. You will notice that in the console appear logs of the application running


### Access Locally ###
Microservices endpoints:

**Get Weather By City**

http://localhost:8080/weather/city/london

**Get Weather by coordinates (latitude and longitude)**

http://localhost:8080/weather/latitude/18.0/longitude/-92.6667

As a security layer, you need to send an API Key. That information can be found in the properties of the app. The same goes for the Database (MySql instance needed).

When the app is deployed it can be used through Swagger API:

http://localhost:8080/swagger-ui/#
