FROM openjdk:8-jre-slim

WORKDIR /app

COPY target/*.jar /app/Livraria.jar

EXPOSE 8180

CMD java -XX:+UseContainerSupport -jar Livraria.jar
