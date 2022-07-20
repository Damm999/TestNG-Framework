# #
# # Build stage
# #
# FROM maven:3.6.0-jdk-11-slim AS build

# # Update aptitude with new repo
# RUN apt-get update

# # Install software 
# RUN apt-get install -y git

# # Download Chrome Browser
# RUN  wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb

# # Install Chrome Browser
# RUN sudo apt install ./google-chrome-stable_current_amd64.deb

# # Clone repo
# RUN git clone https://github.com/Damm999/TestNG-Framework.git /home/testng-repo
# WORKDIR /home/testng-repo
# RUN ls -lah

# # Run Code
# RUN mvn clean install



#
# Build Stage
#
FROM maven:3.6.0-jdk-13-alpine
# #
# #Owner
# #
LABEL "java.com"="TestNG"
LABEL author="Krishna Kaushik"
LABEL version="1.0"
WORKDIR /kk-testng-libs
COPY src/main  /kk-testng-libs/src/main
COPY pom.xml  /kk-testng-libs/pom.xml
COPY testNG.xml  /kk-testng-libs/testNG.xml

RUN  ls -lah && mvn clean package

<<<<<<< Updated upstream
# Install software 
RUN apt-get install -y wget

# Install software 
RUN apt-get install -y git

WORKDIR /home/testng-repo

# Download Chrome Browser
RUN  wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb

# Install Chrome Browser
RUN sudo apt install ./google-chrome-stable_current_amd64.deb

# Clone repo
RUN git clone https://github.com/Damm999/TestNG-Framework.git

RUN ls -lah

# Run Code
RUN mvn clean install
=======
>>>>>>> Stashed changes
