FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD gateway-1.0-SNAPSHOT.jar app.jar
##for CentOs is bash ,for ubuntu is sh
RUN sh -c 'touch /app.jar'
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
