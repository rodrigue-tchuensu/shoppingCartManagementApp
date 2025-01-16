# Stage 1: Build the application with maven tools and generate the .jar
FROM maven:3.9.9-amazoncorretto-17-alpine AS build

# copy the pom.xml file
COPY ./pom.xml ./pom.xml

# build all dependencies
RUN mvn dependency:go-offline -B

# copy source code files
COPY ./src ./src

# build for release
RUN mvn package -DskipTests

# Stage 2: Run the application
FROM openjdk:25-slim

# set workdir
WORKDIR /app

# copy over the built artifact from the maven image
COPY --from=build target/ShoppingCartManagement-*-SNAPSHOT.jar ./

# set the startup command to run your binary
CMD java -jar ShoppingCartManagement-*-SNAPSHOT.jar