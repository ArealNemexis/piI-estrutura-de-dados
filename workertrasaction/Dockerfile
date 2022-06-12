FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} worker-transaction.jar
ENTRYPOINT ["java", "-jar", "/worker-transaction.jar"]