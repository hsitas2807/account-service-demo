# Flight Connection Demo Complete Assignment

* [Required Software](#required-software)
* [Feature included](#feature-included)
* [How to run the Program](#how-to-run-the-program)
* [How to generate Code coverage report](#How-to-generate-Code-coverage-report)

This project is an utility which will be used to find all possible flights arriving to a particular city which can be connected with all flights departuring from the same city. For example, if you want to travel from Mumbai (BOM) to New York (JFK) via Dubai (DXB), the utility will help us to find all possible flights arriving to Dubai from Mumbai which can connect with all flights departuring from Dubai to New York City.


## Required software
* Intellij IDEA/Eclipse
* Java JDK 8+
* JDK and Maven installed and Set in your classpath
* Clone/download the account-service-demo project (https://github.com/hsitas2807/flight-connection-demo/tree/master)

## Feature included

## 1.	Account Service Rest(account-service-rest):The service should expose end-points to “return account summary for users and admin based on their role” .
## Access Control Specifications:
## 1.The ‘admin’ can perform all the requests (specify date and amount range).
## 2.The ‘user’ can only do a request without parameters which will return the three months back statement.
## 2. When the test user tries to specify any parameter, then HTTP unauthorized (401) access
error will be sent.
The user cannot login twice (the user should logout before login).
The session time out is 5 minutes.
    ## Technology: 
    Spring Boot Service, 
    Spring Data for data access, 
    H2DB as data store (prepare required data at the service start-up time)
    Unit testing .
    Actuator Endpoints Enabled at path :http://localhost:8090/actuator/health
    Swagger Api Docs:-http://localhost:8090/v2/api-docs
    Swagger UI:-http://localhost:8090/swagger-ui/#/flight-resource
    Swagger UI or Postman can be used to perfrom Request opertion on account-service-rest.

## Request Headers:-
	"Access-Control-Allow-Origin": "*",
        "Content-type": "application/json; charset=UTF-8",
        "Accept": "application/json"


## How to run the Program
* Import the project into Intellij IDEA/Eclipse.
* Makes sure jdk is set for all modules in Intellij.(Check Modules Settings and project setting to configure JDK)
* Run Maven commands from Project:-It will build clean and build the entire application.
    1. mvn clean
    2. mvn package or mvn install( Will push the UI build foler to cloud-api-gateway /target/classes/static/)
* Bring the connection builder service UP by Running Main class ConnectionBuilderApplication.java or we can also use command from cmd line java -jar target/connection-builder-service-1.0-SNAPSHOT.jar
* Bring master-dat-service UP by Running Main class MasterDataApplication.java or we can also use command from cmd line java -jar target/master-data-service-1.0-SNAPSHOT.jar.
* Run the cloud-api-gateway application by running inside the cmd from cmd line inside directory
  {YOUR_PATH}\flight-connection-demo\cloud-api-gateway java -jar target/master-data-service-1.0-SNAPSHOT.jar
* UI will be UP on http://localhost:8089/

## How to generate Code coverage report
* Run mvn clean install command from Parent Directory.
* It will run the test casess and will genrate aggregated Code coverage report using jacoco.
* Report can be veiwed from parent target folder under path  ( {YOUR_PATH}\\flight-connection-demo\target\reports).Open the index.html in any brower to view the report.


     


