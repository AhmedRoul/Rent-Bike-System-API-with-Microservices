FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21
LABEL maintainer="ahmednagy12474@gmail.com"
WORKDIR /app


COPY target/BikeService-0.0.1-SNAPSHOT.jar /app/BikeService.jar


ENTRYPOINT ["java", "-jar", "BikeService.jar"]
