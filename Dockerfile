FROM ubuntu

# instalacao do oracle jdk 8
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y  software-properties-common && \
    add-apt-repository ppa:webupd8team/java -y && \
    apt-get update && \
    echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y --allow-unauthenticated oracle-java8-installer

WORKDIR /opt

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

CMD ["bash"]
