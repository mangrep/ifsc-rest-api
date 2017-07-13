FROM java:openjdk-8-jdk
MAINTAINER Manish kumar <manishchoudhary55@gmail.com>

#jvm tuning 
RUN sed -i -e 's#dev/random#dev/./urandom#g' /etc/java-8-openjdk/security/java.security

RUN echo "LC_ALL=en_US.UTF-8" >> /etc/environment
RUN echo "LANG=en_US.UTF-8" >> /etc/environment

COPY . /bank-apis/

EXPOSE 8080
CMD ["java", "-jar", "-Xms256m", "-Xmx1536m",  "-XX:MaxNewSize=256m", "/bank-apis/target/bankAPIs-v0.0.5.jar"]
