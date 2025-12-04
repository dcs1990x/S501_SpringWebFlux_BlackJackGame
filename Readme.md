# 🎰 Task S5.01 – Blackjack API with Spring Boot & WebFlux
## 📋 Project Overview

This project consists of developing a reactive REST API that manages a Blackjack game using Spring Boot and Spring WebFlux.
The application interacts with two different databases:

- PostgreSQL → stores player information and ranking results and game logs   
- MongoDB → handles game state and non-relational data   

The project follows clean architecture and back-end best practices, including: 

✔ Fully reactive backend (Mono / Flux)   
✔ Dual database configuration   
✔ Centralized exception handling   
✔ Unit & integration testing   
✔ Swagger documentation   
✔ Docker multi-stage build   
✔ Deployment to Render   

## 💻 Technologies Used

- Java 21   
- Spring Boot 3.x    
- Spring WebFlux   
- Reactor (Flux & Mono)   
- Spring Data MongoDB Reactive   
- Spring Data JPA (MySQL)   
- R2DBC PostgreSQL 1.0.7   
- MongoDB 6   
- Lombok   
- Jakarta Validation API   
- JUnit 5 & Mockito   
- Swagger/SpringDoc 2.8.13    
- Docker   
- Render (Deployment)   

## 🃏 Blackjack Game API

This backend application implements the entire logic required to operate a Blackjack game:   

- Creating a new game   
- Making playable actions (Hit, Stand)   
- Managing cards and hands   
- Managing players and games information   
- Returning player rankings   
- Deleting games   
- Retrieving detailed game state   

 📦 Project Structure:   

blackjack_app   
├── CustomSwaggerConfig   
├── controller   
├── domain   
├──── dtos   
├──── entities   
├──── game_model   
├── exceptions  
├── repository  
├──── GameEntityRepository   
├──── GameStateRepository   
├──── PlayerRepository   
├── service   
└── BlackJackAppApplication(Main class)   

## 🔄 REST Endpoints   

Action&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Method&emsp;&emsp;&emsp;Endpoint&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Description   

Create new game&emsp;&emsp;&emsp;POST&emsp;&emsp;&emsp;/game/new&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Creates a new Blackjack game   
Get game details&emsp;&emsp;&emsp;GET&emsp;&emsp;&emsp;&emsp;/game/{gameId}&emsp;&emsp;&emsp;&emsp;Retrieves detailed info for a specific game   
Play move&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;POST&emsp;&emsp;&emsp;/game/{gameId}/play&emsp;&emsp;Executes a player action (hit/stand)   
Delete game&emsp;&emsp;&emsp;&emsp;&emsp;DELETE&emsp;&emsp;/game/{gameId}/delete&emsp;Deletes an existing game   
Player ranking&emsp;&emsp;&emsp;&emsp; GET&emsp;&emsp;&emsp;&emsp;/ranking&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; Returns ranking sorted by player performance   
Update player name&emsp;&emsp;PUT&emsp;&emsp;&emsp;&emsp;/players/{playerId}&emsp;&emsp; Updates an existing player's name   

## 🧠 User Stories & Acceptance Criteria   
➕ Create Game   

✔ Returns 201 Created   
⚠️ Returns 400 Bad Request if player name is invalid   

📄 Get Game Details   

✔ Returns 200 OK with detailed game info   
⚠️ Returns 404 Not Found if game ID does not exist   

🎮 Play Move   

✔ Returns 200 OK with updated game state   
⚠️ Returns 404 Not Found if game ID is invalid   
⚠️ Returns 400 Bad Request for invalid moves   

❌ Delete Game   

✔ Returns 204 No Content   
⚠️ Returns 404 Not Found if game ID doesn't exist   

🏆 Get Player Ranking   

✔ Returns 200 OK with sorted player list   

✏️ Update Player Name   

✔ Returns 200 OK with updated player data   
⚠️ Returns 400 Bad Request if name is invalid   

## ⚙️ Project Configuration   
spring.application.name=blackjack-app   
========== SERVER ==========   
server.port=9000   

============ MONGODB CONFIGURATION ============   
spring.data.mongodb.uri=mongodb://localhost:27017/blackjackdb   
spring.data.mongodb.database=blackjackdb   

============ POSTGRESQL R2DBC CONFIGURATION ============   
spring.r2dbc.url=r2dbc:postgresql://localhost:5432/blackjackdb   
spring.r2dbc.username=postgres   
spring.r2dbc.password=   

R2DBC Connections pool:   
spring.r2dbc.pool.enabled=true   
spring.r2dbc.pool.max-size=10   

============ LOGGING ============   
logging.level.org.springframework.data.mongodb=DEBUG   
logging.level.org.springframework.data.r2dbc=DEBUG   

## 📘 Swagger API Documentation

Available at: 
👉 http://localhost:9000/swagger-ui.html

## 🐳 Dockerfile (Multi-Stage Build)   

### Building phase:   
```
FROM maven:3.9-eclipse-temurin-21 AS builder   
WORKDIR /app   
COPY pom.xml .   
COPY src ./src   
RUN mvn clean package -DskipTests   
```
### Execution phase:   
```
FROM eclipse-temurin:21-jre   
WORKDIR /app   
```
### Copy Jar file during building phase:   
```
COPY --from=builder /app/target/*.jar app.jar   
```
### Creating a non-root user for safety:   
```
RUN addgroup --system spring && adduser --system spring --ingroup spring   
USER spring:spring   
```
### Running the app:   
```
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]   
```
## 🚀 How to Run   

1️⃣ Clone Repository   
```
git clone https://github.com/dcs1990x/S501_SpringWebFlux_BlackJackGame.git   
```
2️⃣ Build Project   
```
mvn clean package -DskipTests   
```
3️⃣ Run Application   
```
java -jar target/blackjack-api.jar   
```

🐋 Docker Instructions    

Build Image:   
```
docker build -t blackjack-app:latest .
```

Run Container:   
```
docker run -d -p 9000:9000 --name blackjack-api blackjack-app:latest
```

## 🌍 Deployment   

✔ Deploy on Render

- Log into Render   
- Create new Web Service   
- Provide Docker image from Docker Hub   
- Render will auto-deploy the container

## 👨‍💻 Author

Developed by Daniel Caldito Serrano as part of the Java Back-end Development Bootcamp organized by IT Academy.