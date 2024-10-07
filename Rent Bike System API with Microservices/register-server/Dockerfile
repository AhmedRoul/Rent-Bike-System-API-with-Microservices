FROM eclipse-temurin:21
LABEL maintainer="ahmednagy12474@gmail.com"
WORKDIR /app

COPY target/register-server-0.0.1-SNAPSHOT.jar /app/BikeService.jar


ENTRYPOINT ["java", "-jar", "BikeService.jar"]
