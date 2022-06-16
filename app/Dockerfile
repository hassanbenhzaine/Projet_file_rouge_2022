FROM openjdk:17-jdk-slim

ENV POSTGRES_PASSWORD=postgres

ADD target/crm-0.0.1-SNAPSHOT.jar /home/app.jar

WORKDIR /home

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
