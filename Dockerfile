FROM openjdk:17-jdk as build

COPY . /usr/src/myapp
WORKDIR /usr/src/myapp

EXPOSE 8080
ENTRYPOINT ["java","-jar","challenge-0.0.1-SNAPSHOT.jar"]
