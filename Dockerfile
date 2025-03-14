FROM maven:3.8.8-eclipse-temurin-21-alpine AS build
WORKDIR /app

COPY pom.xml /app/pom.xml
COPY src /app/src

RUN mvn dependency:go-offline
RUN mvn clean package -DskipTests

FROM amazoncorretto:21.0.4-alpine3.18 AS runtime
WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
