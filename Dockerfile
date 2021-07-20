#
# Build stage
#
FROM ubuntu:20.04
WORKDIR $HOME
RUN mvn clean install
