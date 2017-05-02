FROM java:openjdk-8-jdk
MAINTAINER Manish kumar <manish.choudhary@livquik.com>

#jvm tuning 
RUN sed -i -e 's#dev/random#dev/./urandom#g' /etc/java-8-openjdk/security/java.security

#RUN apt-get update

RUN echo "LC_ALL=en_US.UTF-8" >> /etc/environment
RUN echo "LANG=en_US.UTF-8" >> /etc/environment

COPY . /bank-api/  

CMD ["/bin/bash","&" ]
