# Microservice: `<weather-service>`

### Component Diagram

The **weather-service** follows the simplest architecture pattern defined for Microservices.

The diagram presents the following components and relationships:

 1. **weather-service**. This component is the one described by this documentation.
 2. **Postgres Database**. Database from which this service retrieve the business information that delivers.
  
#### Endpoints

Assuming there are variables `server` and `port` are defined as the IP or _server name_ and _port_ where this microservice is running, the following endpoints are available:

   GET: http://{server}:{port}/weatherservice/v1/api/weather/city/{cityName}
   GET: http://{server}:{port}/weatherservice/v1/api/weather/latitude/{latitude}/longitude/{longitude}
  
#### Database

The webservice reads on the database. The related tables are:
  - `weather_history` Insert operations only.

## Version History

| Version  | Date          | Description  |
| ---------|---------------| ------------ |
| 1.0.0    | 2022-01-31    | README.md added along with the initial documentation.|
| 1.0.1    | 2022-01-31    | Added:Functionality for weatherservice/weather/city/{cityName} endpoint.|
| 1.0.2    | 2022-02-01    | Added:Functionality for weatherservice/weather/latitude/{latitude}/longitude/{longitude} endpoint.|
| 1.0.3    | 2022-02-01    | Added:Testing and validations.|
| 1.0.4    | 2022-02-01    | Added:API definition.|


### About Microservice

	Repo:
		https://github.com/angelHernandez182/knowledgeboost-challenge/tree/angel-hernandez
		
	For API documentation you can access to swagger:
		
		http://localhost:8080/swagger-ui/index.html#/
		
	Devs:
		accesToken:
			50b374349ec084f8dd4dbb2371d54e26
		
	Available Urls:
	
	http://localhost:8080/weatherservice/weather/city/{cityName}?accessToken=50b374349ec084f8dd4dbb2371d54e26
	http://localhost:8080/weatherservice/weather/latitude/38.5003/longitude/-98.5006?accessToken=50b374349ec084f8dd4dbb2371d54e26
	
	Technologies and tools:
	
		- java 8
		- spring-boot
		- spring cloud
		- junit
		- lombok
		- swagger
		- postgresql
		
	
	References:
		https://openjdk.org/projects/jdk8/
		https://maven.apache.org/guides/
		https://start.spring.io/
		https://projectlombok.org/features/
		https://spring.io/projects/spring-cloud-openfeign
		https://devops.datenkollektiv.de/banner.txt/index.html
		https://www.postgresql.org/docs/
		https://swagger.io/docs/
		https://www.baeldung.com/swagger-apioperation-vs-apiresponse
		