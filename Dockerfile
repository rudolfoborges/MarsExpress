FROM java:8

EXPOSE 8080

WORKDIR /opt

ADD target/mars-express-0.0.1.jar mars-express-0.0.1.jar

RUN sh -c 'touch /opt/mars-express-0.0.1.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/mars-express-0.0.1.jar"]
