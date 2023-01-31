# Microservice: `<weather-service>`

### Component Diagram

The **weather-service** follows the simplest architecture pattern defined for Microservices.

The diagram presents the following components and relationships:

 1. **weather-service**. This component is the one described by this documentation.
 2. **Postgres Database**. Database from which this service retrieve the business information that delivers.
  
#### Endpoints

Assuming there are variables `server` and `port` are defined as the IP or _server name_ and _port_ where this microservice is running, the following endpoints are available:

   GET: http://{server}:{port}/weatherservice/v1/api/weather/city/{cityName}
   GET: http://{server}:{port}/weatherservice/v1/api/weather/latitude/{latitude}/longitude/{longitude
  
#### Database

The webservice reads on the database. The related tables are:
  - `PROCESSED_IMAGES ` Read operations only.

## Version History

| Version  | Date          | Description  |
| ---------|---------------| ------------ |
| 1.0.0    | 2022-01-31    | README.md added along with the initial documentation.|


### About Microservice

	Repo:
		https://github.com/glb-code-challenges/knowledgeboost-challenge
		
	For API documentation you can access to swagger:
		
		http://localhost:8080/swagger-ui/index.html#/
		
	
