FROM ubuntu

EXPOSE 8080

# instalacao do oracle jdk 8
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y  software-properties-common && \
    add-apt-repository ppa:webupd8team/java -y && \
    apt-get update && \
    echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y --allow-unauthenticated oracle-java8-installer

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

WORKDIR /opt

ADD target/mars-express-0.0.1.jar mars-express-0.0.1.jar

RUN sh -c 'touch /opt/mars-express-0.0.1.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/mars-express-0.0.1.jar"]