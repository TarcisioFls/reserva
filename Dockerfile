FROM maven:3.8.3-openjdk-22-slim AS build
COPY /src /reservation/src
COPY /pom.xml /reservation
WORKDIR /reservation
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk-slim
ARG JAR_FILE=target/*.jar
COPY --from=build /reservation/${JAR_FILE} /reservation/reservation.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/reservation/reservation.jar"]