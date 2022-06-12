FROM openjdk:18
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} scheduler.jar
ENTRYPOINT ["java", "-jar", "/scheduler.jar"]