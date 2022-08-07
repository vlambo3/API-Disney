# Challenge BackEnd - Java

## Pre-aceleración ALKEMY
    
    
### Target
  Develop an API using Java SpringBoot and SpringSecurity, following the REST pattern.
  The API that allows to know and modify the catalog of characters, movies and genres of Disney movies
  
### Requirements
  - [x]  Java 11
  - [x]  Maven 3.8.6
  - [x]  MySQL 8
  
### Setup Steps

**1. Clone repository**

  [git clone](https://github.com/vlambo3/pre-aceleracion-vanina-godoy) 


**2. Create DataBase in MySQL**
```mysql
        CREATE DATABASE disney;
```
        
**3. Change MySQL server username and password in application.properties file.**

  * Open  `disney-api/src/main/resources/application.properties` file
      
  * Change the username and password as you have configured your MySQL

      + `spring.datasource.username=root`
  
      + `spring.datasource.password=root`
      
**4. Build and Run**

  The app will run at: http://localhost:8080/  
