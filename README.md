# Receipt Processor Application
This is a Spring Boot application that connects to local H2 in-memory database to provide API's for storing receipts and fetching points for respective receipts.

#### Instructions to Run the Application
* If you are using Docker: 

```sh
docker build -t receipt-processor . 
docker run -p 8080:8080 receipt-processor   
```
# Repo organization

#### Receipt Processor
* `ReceiptProcessorApplication.java`: Main Application
* `ReceiptController.java`: Provides REST endpoints for respective operations - process and points
* `ReceiptService.java`: Receives call from ReceiptController to store and find data from repository
* `Receipt.java`: Data Model for storing Receipts
* `Item.java`: Nested data model to store items in Receipts
* `ReceiptRepository.java`: JPA Repository that allows CRUD operations.
* `application.properties`: Configuration file that provides connection properties for local h2 database connection
* `pom.xml`: Dependencies manager

#### Instructions to test the Application

* Send receipt for processing - 
```sh
curl --location --request POST 'http://localhost:8080/receipts/process' \
--header 'Content-Type: application/json' \
--data-raw '{
  "retailer": "M&M Corner Market",
  "purchaseDate": "2022-03-20",
  "purchaseTime": "14:33",
  "items": [
    {
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    }
  ],
  "total": "9.00"
}' 
```
* Fetch the points using Receipt ID returned as part of previous request
```sh
curl --location --request GET 'http://localhost:8080/receipts/{id}/points'
```
