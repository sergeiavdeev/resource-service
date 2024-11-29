FROM openjdk:21

WORKDIR /app

ADD target/resource-service-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "resource-service-0.0.1-SNAPSHOT.jar"]