FROM openjdk:15-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/application_deploy.properties application.properties
ENTRYPOINT ["java","-jar", "--enable-preview", "/app.jar"]
