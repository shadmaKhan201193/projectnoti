FROM openjdk:17-alpine
COPY /target/notification-service-0.0.1.jar usr/src/notification-service-0.0.1.jar
EXPOSE 8080
CMD ["java","-jar","/usr/src/notification-service-0.0.1.jar"]
