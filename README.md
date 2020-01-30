Payconiq Assignment - Stock Management

The expectation is to create a RESTful Java application to create, update, view and track changes to the price of the stock.

The application is designed as described below,

- Initial list of stock is loaded from a JSON file on context initialization. 
  This is done through StockManagementAppConfig.class. 
  This class loads two Map collection beans, one to maintain master list of stocks
  and the other one to track the changes to the stock price.

- On top of the config, REST controllers and Service implementations are done.
- On doing POST /api/stocks, an ID will be generated randomly using Math.Random. 
  This is limited based an min and max value is loaded from application.properties
- Basic input validations are done and custom exception message are handled
- logback.xml has been configured to do the logging.
- CORS configuration has been handled in configuration class
- JUnit tests have been created and code coverage of around 95% is achieved.

A basic frontend has been created and can be accessed at http://localhost:8080/stockmanagement

API documentation can be accessed at http://localhost:8080/stockmanagement/swagger-ui.html#/

Postman collection & Code coverage report has also been checked in with the source code.

HOW TO RUN:
- Import the project into any IDE
- Run the StockmanagementApplication.java as a sprint boot application.
  The application should be up in no time.


