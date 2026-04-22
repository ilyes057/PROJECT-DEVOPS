FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /app

COPY pom.xml .
COPY src ./src
COPY README.md .
COPY AUTHORS .

RUN mvn -q -DskipTests compile

CMD ["mvn", "verify"]
