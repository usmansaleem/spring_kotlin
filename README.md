# Spring Boot, Kotlin REST and JPA

API Usage:

Tenants:

Get All Tenants
http://localhost:8080/api/v1/tenant

Get specific tenant
http://localhost:8080/api/v1/tenant/1

Add new tenant (POST. Content-Type: application/json)
http://localhost:8080/api/v1/tenant
Payload:
{
"name": "UziS",
"weeklyRentAmount": 370.0
}

TODO:
Create rent receipt for given tenant (POST. Content-Type: application/json)
http://localhost:8080/api/v1/tenant/1/rentreceipts
{
"amount":400
}

Get All rent receipts for tenant
http://localhost:8080/api/v1/tenant/1/rentreceipts

Get All tenants with receipts created in last N hours
http://localhost:8080/api/v1/tenant/rentreceipts/3


References:

http://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-gradle-plugin.html
https://kotlinlang.org/docs/tutorials/spring-boot-restful.html
