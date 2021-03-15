# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine
# Add Maintainer Info
LABEL maintainer="tafadzwalnyamukapa@gmail.com"
#Mapping container to host temp folder
VOLUME /tmp
# Make port 8080 available to the world outside this container
EXPOSE 8080
# Add Copy the application's jar to the container as app.jar
ADD ./target/spring-boot-rest-api-0.0.1-SNAPSHOT.jar app.jar
#This is where you configure how the application is executed inside the container.
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]


##NOTES
# 1. Before you build this Image first run to build the jar file
#$ mvn clean package $ mvn clean package  or mvn clean package -DskipTests   //to skip tests

# 2. After building the jar file now we can build the DOCKER IMAGE by running
# $ docker build -t <image-name-here>

# 3. To run the Image
# $ docker run -p 8080:8080 <image-name-here>

    # To run the the image to in the background you use the -d detach mode
    # $ docker run -d -p 8080:8080 <image-name-here>

#4. Then finally to run the app build the containers with  docker-compose with -d detachment mode optional
# $docker-compose up -d