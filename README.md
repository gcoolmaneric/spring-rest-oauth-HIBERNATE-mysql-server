This project is a very simple REST and OAuth server template with high performance in Spring framework.
Since the authenticated token is stored in MYSQL, its easy to scale up your server to meet
high user traffic. 

## Why has this project been created ? 
I found it difficult to develop a secure REST API Server from scratch with features like OAuth, MYSQL, HIBERNATE, MYSQL Token Store, JDBCTemplate, and Https. It will take lots of efforts to put all the features together and make them work properly. In order to reduce redundant work, I therefore created the REST API server template in Spring framework.

With this server template, you can focus on developing REST API to meet your business needs without being distracted by other technologies. You can also improve this project or fork it as needed. 


## Project

This project includes the following features.

	MVN
	Spring-Boot
	REST
	JPA
	MYSQL + HIBERNATE 
	MYSQL Token Store
	JDBCTemplate
	Https

## Get Started 

Import userdb.sql into your database.

```
import database/userdb.sql into MYSQL database
```

## Enable SSL

```
// Create key store 
bash
keytool -genkey -alias tomcat -keyalg RSA

// Edit 
vim /src/main/resources/application.properties

// Uncomment the following lines and set up your key store path
## SSL
server.port=8443
server.ssl.key-store=./src/main/resources/your.jks
server.ssl.key-store-password=your store passowrd
server.ssl.key-password=your pass

```

## Building

You need Java (1.7 or better) and Maven (3.0.5 or better):

```
$ mvn clean package
$ mvn package
$ java -jar target/*.jar
...

// Http
<app starts and listens on port 8080>

// Https
<app starts and listens on port 8443>

```

Here are some curl commands to use to get started:

```
// Get Token
curl -k -X POST -d 'grant_type=client_credentials' --user 'my-client-with-secret:secret' https://localhost:8443/oauth/token
{"access_token":"bf12a9c8-c341-44a6-9ce6-084a8ba86652","token_type":"bearer","expires_in":43199,"scope":"read"}

// hasUserId GET
curl -k -H "Authorization: 5470484a-148d-479f-988e-89dfce617bb1" https://localhost:8443/user/hasUserId?uid=336u594534
{"status":200, "userId": 336u594534}

// Twitter Login POST
curl -k -H "Authorization: Bearer b61db2dd-0af4-4e3c-b2b9-7c307a9d7c69" -X POST -H "Content-Type: application/json" -d "{\"twitterId\": \"0926841831\", \"deviceId\": \"2222\"}" https://localhost:8443/user/twitterLogin

```

## How to optimize MYSQL connection pool ?
```
// Edit
vim /src/main/resources/application.properties

// Configure initial and maximal connections 
spring.datasource.initialSize= 15
spring.datasource.maxActive= 30
```

## How to set up Master/Slave replication in MYSQL ?

```
// Edit
vim /src/main/resources/application.properties

## Master and Slave
spring.datasource.url = jdbc:mysql:replication://localhost1:3306,localhost2:3306/userdb
```
