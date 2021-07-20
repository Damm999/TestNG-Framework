#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
#WORKDIR /TestNG-Framework
RUN ls -lah
RUN mvn clean install
