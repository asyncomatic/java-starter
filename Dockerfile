FROM maven:3.9-eclipse-temurin-17 as builder
RUN mkdir /app
WORKDIR /app
COPY . .
RUN mvn install

FROM eclipse-temurin:17
COPY --from=builder /app/target/java-starter-jar-with-dependencies.jar \
./java-starter.jar

ENTRYPOINT ["java", "-cp", "java-starter.jar", "io.github.asyncomatic.starter.ExecutorService"]
