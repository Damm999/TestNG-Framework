#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build

# Update aptitude with new repo
RUN apt-get update

# Install software 
RUN apt-get install -y wget

# Install software 
RUN apt-get install -y git



# Download Chrome Browser
RUN  mkdir /home/testng-rep && cd /home/testng-repo && wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb

# Install Chrome Browser
RUN cd /home/testng-repo && sudo apt install ./google-chrome-stable_current_amd64.deb && cd /

# Clone repo
RUN git clone https://github.com/Damm999/TestNG-Framework.git /home/testng-repo
WORKDIR /home/testng-repo
RUN ls -lah

# Run Code
RUN mvn clean install
