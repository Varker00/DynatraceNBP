FROM maven:3.9.1-eclipse-temurin-17-alpine AS build
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ["chmod", "+x", "./mvnw"]
RUN mvn dependency:resolve
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]