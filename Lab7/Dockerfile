FROM eclipse-temurin:19


WORKDIR /app

RUN mkdir -p /app/logs


COPY target/Lab7-3.5.0.jar ./target/Lab7-3.5.0.jar
# COPY pom.xml ./
# COPY src/ ./src/
# RUN mvn package

CMD ["java", "-jar", "/app/target/Lab7-3.5.0.jar"]
