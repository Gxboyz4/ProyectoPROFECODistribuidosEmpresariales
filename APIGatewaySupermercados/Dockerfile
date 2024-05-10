FROM openjdk:latest

RUN mkdir /usr/local/tomee \
    && curl -L https://downloads.apache.org/tomee/tomee-10.0.0-M1/apache-tomee-10.0.0-M1-plus.tar.gz \
    | tar -zxf - -C /usr/local/tomee --strip-components=1

COPY target/APIGatewaySupermercados-1.war /usr/local/tomee/webapps/

EXPOSE 8080

CMD ["/usr/local/tomee/bin/catalina.sh", "run"]
