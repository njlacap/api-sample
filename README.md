# Read Me First
* Java version: 21
* Spring Boot version: 3.2.2
***
# Getting Started
### Build the Project
```
mvn clean install
```
### Run the Project
```
mvn spring-boot:run -Dspring-boot.run.profiles=local
```
***
## REST CONTRACT
* ### GET | By Product ID
### ```http://localhost:8080/inventory/products/{id}```
```
{
    "id": 1,
    "productName": "hotdog"
}
```
***
* ### GET | All Products
### ```http://localhost:8080/inventory/products```
```
[
    {
        "id": 1,
        "productName": "hotdog"
    },
    {
        "id": 2,
        "productName": "cheesedog"
    },
    {
        "id": 3,
        "productName": "chickendog"
    }
]
```
***