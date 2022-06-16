FROM openjdk:8-jre-slim-buster

COPY target/*.jar home/wdcmanager/wdcmanager.jar

WORKDIR /home/wdcmanager

EXPOSE 8080

CMD java -jar wdcmanager.jar