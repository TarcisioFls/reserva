FROM maven:3.9.8-amazoncorretto-21 AS build
COPY src /reserva/src
COPY pom.xml /reserva
WORKDIR /reserva
RUN mvn clean package -Dmaven.test.skip=true

FROM amazoncorretto:21
ARG JAR_FILE=target/*.jar
COPY --from=build /reserva/${JAR_FILE} /reserva/reserva.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/reserva/reserva.jar"]