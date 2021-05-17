FROM adoptopenjdk/openjdk15:alpine-jre

# Dockerfile author / maintainer
MAINTAINER Sachin Goyal <sachin.goyal.se@gmail.com>

VOLUME /opt/user

ADD build/libs/user*.jar /opt/user/user.jar

ENTRYPOINT ["java","-jar","/opt/user/user.jar"]
