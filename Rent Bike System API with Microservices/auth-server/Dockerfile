FROM eclipse-temurin:21
LABEL maintainer="ahmednagy12474@gmail.com"
WORKDIR /app

COPY target/auth-server-0.0.1-SNAPSHOT.jar  /app/auth-server.jar

ENTRYPOINT ["java", "-jar", "auth-server.jar"]
