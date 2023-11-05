FROM openjdk:17

WORKDIR /app

COPY mvnw .
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw  install -DskipTests

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
