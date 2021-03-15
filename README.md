# zss-springboot-interview-sol
A Spring RESTAPI  with Docker that  consumes to a Rest Service Using RestTemplate.

## Getting Started

### Installation

#### 1. Docker
To install and run the application you can either chose to run with Docker assuming that you have already setup Docker in you Development machine

Steps:
1. Build the application by running

```cmd
mvn clean install -DskipTests=true
```
2. Build your container with docker-compose in detachement mode
```cmd
docker-compose up -d
```
Alternatively if you want to see the logs as you are running the app you can spin up the containers without the -d flag
* **NB** Make sure you have pulled the **postgres** from docker hub by simply running "docker pull postgres"

#### 2. Directly run the Application 

To directly run the app make sure ro replace the  postgres database settings in application.properties file

```yml
spring.datasource.url=jdbc:postgresql://<your-host>:5432/<your-database-name>
spring.datasource.username=<database-user-name>
spring.datasource.password=<database-passwprd>
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=create

```


### ENDPOINTS
The table below shows the exposed end points for the Book & Category Management

** Book

| Method                        | EndPoint                                 | REQUEST METHOD     |  
| ----------------------------- |:----------------------------------------:|:------------------:|
| Save Book                     | http://127.0.0.1:8080/book/save          | POST               |
| Get All Books                 | http://127.0.0.1:8080/book/all           | GET                |
| Get Book By Category          | http://127.0.0.1:8080/book/category/<book_id_here>| GET                |
| Get Book By Id                | http://127.0.0.1:8080/book/<book_id_here> | GET                |
| Update Book                   | http://127.0.0.1:8080/book/<book_id>      | PUT               |
| Delete Book                   | http://127.0.0.1:8080/book/<book_id>      | DELETE            |
| Purchase Book                 | http://127.0.0.1:8080/book/purchase       | POST              |

** Category

| Method                        | EndPoint                                 | REQUEST METHOD     |  
| ----------------------------- |:----------------------------------------:|:------------------:|
| Save Category                 | http://127.0.0.1:8080/category/save          | POST               |
| Get All Category              | http://127.0.0.1:8080/category/all           | GET                |
| Get Category By Id            | http://127.0.0.1:8080/category/<category_id>| GET                |
| Update Category               | http://127.0.0.1:8080/category/<category_id> | PUT               |
| Delete Category               | http://127.0.0.1:8080/category/<category_id> | DELETE            |


### Examples

Saving a Category your post request should have the following fields
```json
{
	"title":"Thriller"
} 
```

Saving a Book your post request should have the following fields

```json

{
	"title":"Book1",
	"price":13.86,
	"description":"This is book one",
	"categoryId":1
} 
```