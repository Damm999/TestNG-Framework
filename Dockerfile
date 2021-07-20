#
# Build stage
#
WORKDIR $HOME
RUN mvn clean install
