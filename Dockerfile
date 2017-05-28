FROM java:openjdk-8-jdk
MAINTAINER Manish kumar <manishchoudhary55@gmail.com>

#jvm tuning 
RUN sed -i -e 's#dev/random#dev/./urandom#g' /etc/java-8-openjdk/security/java.security

RUN echo "LC_ALL=en_US.UTF-8" >> /etc/environment
RUN echo "LANG=en_US.UTF-8" >> /etc/environment

#mongodb installation
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 0C49F3730359A14518585931BC711F9BA15703C6
RUN touch /etc/apt/sources.list.d/mongodb-org-3.4.list
RUN echo "deb http://repo.mongodb.org/apt/debian jessie/mongodb-org/3.4 main" > /etc/apt/sources.list.d/mongodb-org-3.4.list
RUN apt-get update
RUN apt-get install -y mongodb-org
RUN mkdir -p /data/db
VOLUME /data/db

COPY . /bank-apis/

