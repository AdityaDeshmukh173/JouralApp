FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests
RUN ls -al /app/target  # Debugging line

FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/journalApp-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]