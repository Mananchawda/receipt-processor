# Receipt Processor Application
This is a Spring Boot application that connects to local H2 in-memory database to provide API's for storing receipts and fetching points for respective receipts.

#### Instructions to Run the Application
* If you are using Docker: 

```sh
docker build -t receipt-processor . 
docker run -p 8080:8080 receipt-processor   
```


