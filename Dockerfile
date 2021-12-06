FROM openjdk:11
ADD target/*.jar user-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","user-service.jar"]