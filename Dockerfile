FROM maven:3.8.3-openjdk-22-slim AS build
COPY /src /reserva/src
COPY /pom.xml /reserva
WORKDIR /reserva
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk-slim
ARG JAR_FILE=target/*.jar
COPY --from=build /reserva/${JAR_FILE} /reserva/reserva.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/reserva/reserva.jar"]