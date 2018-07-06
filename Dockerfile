FROM java:8

# Install maven
RUN apt-get update
RUN apt-get install -y maven

#Install required applications
RUN echo 'deb http://httpredir.debian.org/debian jessie-backports main' >> /etc/apt/sources.list.d/jessie-backports.list
RUN apt-get update && \
    apt install -y -t jessie-backports openjdk-8-jre-headless ca-certificates-java && \
    apt-get install -y xvfb wget openjdk-8-jre && \
    wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    dpkg --unpack google-chrome-stable_current_amd64.deb && \
    apt-get install -f -y && \
    apt-get clean && \
    rm google-chrome-stable_current_amd64.deb

#Add java env
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

#create folder with tests
RUN mkdir -p /tests

#set workdir
WORKDIR /tests

# Prepare by downloading dependencies
ADD pom.xml /tests/pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

#add dockerfile
ADD Dockerfile /tests/Dockerfile

# Adding source, compile and package into a fat jar
ADD src /tests/src
RUN ["mvn", "package"]

RUN ["mvn", "-DskipTests=true"]

# Make port 5014 available to the world outside this container for test
EXPOSE 5014

#Open the bash
CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "target/assignment4-1.0.jar"]
