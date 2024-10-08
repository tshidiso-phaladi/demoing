## Getting Started

Run below commands to compile/package
```
mvn clean install
```
Next build docker images with below commad:
```
docker-compose up --build
```
* Go to http://localhost:8080/demoing/api/products

 
### cURLs

Get All Products
```
curl http://localhost:8080/demoing/api/products/
```
Get Product by Id
```
curl http://localhost:8080/demoing/api/products/1
```
Create Product
```
curl -X POST http://localhost:8080/demoing/api/products -H "Content-Type:application/json" -d '{"name": "a new product","price": 0.5}'
```
Update Product
```
curl -X PUT http://localhost:8080/demoing/api/products -H "Content-Type:application/json" -d '{"id": 5, "name": "a new product","price": 0.5}'
```
Login/Authenticate
```
curl -X POST http://localhost:8080/demoing/api/users -H "Content-Type: application/x-www-form-urlencoded" -d "name=Phumla&email=phumla.phaladi@gmail.com"
```
Delete Product (generate bearer token prior with curl above)
```
curl -X DELETE http://localhost:8080/demoing/api/products/3 -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvZGVtb2luZy9hcGkvdXNlcnMiLCJzdWIiOiJ0c2hpZGlzby5waGFsYWRpQGdtYWlsLmNvbSIsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9kZW1vaW5nL2FwaS8iLCJpYXQiOjE3MjgzOTM3MzcsImV4cCI6MTcyODM5NDYzNywianRpIjoiM2QxODQ5YjctNzhlOS00NGE5LWFkZWMtNjNhMDJlYzE3N2ZiIn0.eN6uDA43MXEL8RBdDzgfr2XbAIwLeO2xrI9XKvMlDFk"
```


