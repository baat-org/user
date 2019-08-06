FROM java:8

# Dockerfile author / maintainer
MAINTAINER Sachin Goyal <sachin.goyal.se@gmail.com>

VOLUME /opt/baat

ADD target/baat-user*.jar /opt/baat/baat-user.jar

ENTRYPOINT ["java","-jar","/opt/baat/baat-user.jar"]
