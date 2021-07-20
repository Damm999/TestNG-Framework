#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
RUN git clone https://github.com/Damm999/TestNG-Framework.git /home/testng-repo
WORKDIR /home/testng-repo
RUN ls -lah
RUN mvn clean install
