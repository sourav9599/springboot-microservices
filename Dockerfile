FROM openjdk:11
ADD user-service/target/*.jar user-service.jar
EXPOSE 4001
ENTRYPOINT ["java","-jar","user-service.jar"]