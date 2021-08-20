FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} dockertemperature.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/dockertemperature.jar"]