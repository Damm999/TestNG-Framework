#
# Build stage
#
FROM ubuntu:20.04
FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR $HOME
RUN mvn clean install
