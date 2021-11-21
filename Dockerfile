FROM openjdk:8-jre-slim

WORKDIR /app

COPY target/*.jar /app/Livraria.jar

EXPOSE 8080

CMD java -XX:+UseContainerSupport -Xmx512m -Dserver.port=${PORT} -jar Livraria.jar
