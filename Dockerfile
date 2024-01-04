FROM eclipse-temurin:21-jdk-alpine as build
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN chmod +x mvnw && ./mvnw install -DskipTests

FROM eclipse-temurin:21-jre-alpine

RUN addgroup -S app && adduser -S app -G app
RUN mkdir -p /app/pfps && chown app:app /app/pfps
USER app

ARG JAR_FILE=/app/target/*.jar
COPY --from=build $JAR_FILE /app/runner.jar

EXPOSE 8080

CMD exec java -jar /app/runner.jar