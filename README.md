# myRetailService

Retail Spring Application for the pricing at myRetail.

#To Run

git clone git@github.com:samathashetty/myRetailService.git

Import to IDE of your choice by opening the pom.xml.


#To verify the requests
http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

username=sa 
password=sa

CURL Requests: 
1. curl -X GET "http://localhost:8080/products/13860428"
2. curl -X POST "http://localhost:8080/products/13860428"  -H 'Content-Type: application/json' -d '{"id":13860428,"name":"The Big Lebowski (Blu-ray)","current_price":{"id":13860428,"price":9876.0,"currency_code":"USD"}}'

#Coverage Report

mvn package install test jacoco:report

The report is available at ./target/site/jacoco/index.html
