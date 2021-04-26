# Account Service Demo Complete Assignment

* [Required Software](#required-software)
* [Feature included](#feature-included)
* [How to run the Program](#how-to-run-the-program)
* [How to generate Code coverage report](#How-to-generate-Code-coverage-report)

This project is an utility which will be used to generate account statement summary on performing search based on date, amount range and user roles. Users with admin role can perform all the requests based on accountid, date range, amount range and no parameters. Incase of no parameters all search returns last 3 months statement.
Users with non-admin roles can only do one request i.e. without parameters which returns last 3 months statement. If the non admin user tries to specify parameters HTTP unauthorized (401) access error will be sent. Users can not hit same request from different platforms


## Required software
* Intellij IDEA/Eclipse
* Java JDK 8+
* JDK and Maven installed and Set in your classpath
* Clone/download the account-service-demo project (https://github.com/hsitas2807/account-service-demo/)

## Feature included

## 1.	Account Service Rest(account-service-rest):The service should expose end-points to “return account summary for users and admin based on their role” .

    ## Technology: 
    Spring Boot Service, 
    Microsoft Access Db, 
    H2DB as data store to maintain session data (prepare required data at the service start-up time)
    Unit testing .
    Swagger Api Docs:-http://localhost:8090/v2/api-docs
    Swagger UI:-http://localhost:8090/swagger-ui/
    Swagger UI or Postman can be used to perfrom Request opertion on account-service-rest.

## Request Headers:-
	"Access-Control-Allow-Origin": "*",
        "Content-type": "application/json; charset=UTF-8",
        "Accept": "application/json"


## How to run the Program
* Import the project into Intellij IDEA/Eclipse.
* Makes sure jdk is set for all modules in Intellij.(Check Modules Settings and project setting to configure JDK)
* **Put db file on path before runnig the application or running test cases.**
	1. For windows put db file on sample path: //C:/Users/sdixit/Documents/nagarro/JavaTest/accountsdb.accdb
	2. For unix put db file on sample path: ///home/sdixit/Documents/JavaTest/accountsdb.accdb
* Run Maven commands from Project:-It will build clean and build the entire application.
    1. mvn clean
    2. mvn package or mvn install
* Bring the account service rest module UP by Running Main class AccountsApplication.java or we can also use command from cmd line java -jar target/account-service-rest-1.0-SNAPSHOT.jar

## How to generate Code coverage report
* Run mvn clean install command from Parent Directory.
* It will run the test casess and will genrate aggregated Code coverage report using jacoco.
* Report can be veiwed from parent target folder under path  ( {YOUR_PATH}\\account-service-demo\target\reports\jacoco).Open the index.html in any brower to view the report.


     


