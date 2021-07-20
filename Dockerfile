#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build

# Update aptitude with new repo
RUN apt-get update

# Install software 
RUN apt-get install -y git

# Clone repo
RUN git clone https://github.com/Damm999/TestNG-Framework.git /home/testng-repo
WORKDIR /home/testng-repo
RUN ls -lah

# Run Code
RUN mvn clean install
