Project Vision:
The Client wants IT Company to develop a microservice for the retrieval of the current weather data for a given city by using the OpenWeather API.
						
Section 1 - Prerequisites.
JDK 8+.
Account in https://openweathermap.org/ with one API Key.
If you  face issues to get a key, we will provide the response for the endpoints so you can develop the solution required in the Requisites section. 
Cloud repository:
Use the below Git repository to push your data.
https://github.com/glb-code-challenges/knowledgeboost-challenge 
The branch must follow the pattern: https://<REPO>/{userName}


Section 2. - Requisites


Description of Services to be completed by IT Company (the “Services”):			
The microservice should implement two endpoints:
weather/city/{cityName}
weather/latitude/{latitude}/longitude/{longitude}


The response from the calls to the endpoints must be stored in a relational database where you save:
Timestamp of the operation.
Response code.
If the response is an error, save the openWeather cause.
City name.


Section 3	- Delivery.				 						
Push your code to the repository mentioned above in the Prerequisites section.
Write a presentation with a maximum of three slides to:
Introduce yourself - Name, seniority -
Explain the list of technologies used in the implementation.
Your code must compile and run locally - deployment in the cloud is a plus -
Explain your code - 15 minutes maximum. 

Section 4 - Considerations.
Best Practices matters.
Unit test coverage of at least 75% is expected.
Logging is a must.
Code should be committed via PR.
Validate input parameters of all requests.
Exception handling is a must.
The stack of technologies and/or components to be used is up to the developer but it must be aligned with the TP Tech Talks.					

  Section 5 - Documentation
API requests and responses. This can be simple but should be clear in how to call the endpoints and what to expect as responses.	
Architecture Diagrams and/or design diagrams are a plus.
