# revolut-money-transfer-app

Test task  to create a lightweight money transfer app without using any framework like spring/hibernate.

### Tech stack used in the project :
* Jooby mvc as web service framework
* H2 in memory database 
* Apache Maven as build tool
* JUnit5 as a test framework
* Mockito for mocking
* Zerocode with Junit for loand test

### Requirements: ###
Things you need to have installed to run the test project. 

 - Git
 - Apache Maven
 - JDK 1.8

### Running the application: ###

 - Clone the project.

    ```
    git clone https://github.com/reetesh043/revolut-money-transfer-app.git
    ```
    
 - Go to the project.
    ```
    cd revolut-money-transfer-app
    ```
- Run this command
	 
   ``` 
   mvn clean package
   java -jar target/revolut-money-transfer-1.0.0.jar
   ```
   
- The application is deployed at http://localhost:8080/
- You can find the unit test  report at `/revolut-money-transfer-app/target/surefire-reports` and load test report at `/revolut-money-transfer-app/target/zerocode-junit-granular-report.csv`

### Steps to test the app
- Start the application as described above
- Create multiple customers
- Create multiple Accounts by passing customer id as Path Param
- Deposit Money to accounts
- Transfer the money between accounts
- Request and Response for each operation mentioned is described in Available API's section

### Operations

```
  PUT  /api/customer
  GET  /api/customer/{id}
  GET  /api/customer/{id}/accounts
  PUT  /api/account/create/{id}
  GET  /api/account/balance/{accNum}
  POST /api/transfer
  POST /api/withdraw
  POST /api/deposit
  ```
### Available APIs

***Customer API***: 

**Create Customer(PUT)**:

-Request:
```
 curl --location --request PUT 'http://localhost:8080/api/customer' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data-raw '{
	"firstName": "reetesh",
	"lastName": "kumar",
	"address": "UK"`
}'
```
Response:
```
{"Status":"SUCCESS","Message":"Customer is created successfully  with customerID:2"}
```
**GetCustomer(GET)**

-Request:
```
http://localhost:8080/api/customer/1(customerID)
```

Response:
```
{
    "firstName": "reetesh",
    "lastName": "kumar",
    "address": "UK"
}
```
***Account API***

**Create Account(PUT)**

-Request:

```
http://localhost:8080/api/account/create/2 (CustomerID)
```
Response:
```
{
    "Status": "SUCCESS",
    "Message": "Account created successfully with accountNumber: 31XXXX14"
}
```
**Deposit Money(POST)**

-Request:
```
curl --location --request POST 'http://localhost:8080/api/deposit' \
--header 'Content-Type: application/json' \
--data-raw '{
	"accountNumber": "01XXXX66",
	"amount": "5200.600"
}'
```
Response:
```
{
    "accountNumber": "01XXXX66",
    "amount": 5200.600
}
```
**Withdraw Money(POST)**

- Request:

```
curl --location --request POST 'http://localhost:8080/api/withdraw' \
--header 'Content-Type: application/json' \
--data-raw '{
	"accountNumber": "01XXXX66",
	"amount": "126.600"
}'
```
Response:

```
{
    "accountNumber": "93XXXX51",
    "amount": 5074.000
}
```
**Check Available Balance(GET)**

-Request:
```
curl --location --request GET 'http://localhost:8080/api/account/balance/01XXXX66 (accountNumber)'
```
Response:

```
{
    "accountNumber": "01XXXX66",
    "amount": 3200.600
}
```

**Transfer Money(POST)**
Request:
```
curl --location --request POST 'http://localhost:8080/api/transfer' \
--header 'Content-Type: application/json' \
--data-raw '{
    "senderAccNum": "01XXXX66",
    "beneAccNum": "31XXXX14",
    "amount": "1000"
}'
```

Response:
```
{
    "Status": "SUCCESS",
    "Message": "Amount transferred successfully"
}
```
