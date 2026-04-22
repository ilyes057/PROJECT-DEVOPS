FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /build

COPY pom.xml .
COPY src ./src
COPY README.md .
COPY AUTHORS .

RUN mvn -q verify

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /build/target/classes /app/classes

CMD ["java", "-cp", "/app/classes", "fr.uga.devops.ndarray.DemoMain"]
