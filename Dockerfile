#
# Build stage
#
FROM windows
WORKDIR $HOME
RUN mvn clean install
