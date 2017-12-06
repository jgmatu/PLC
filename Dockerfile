FROM java:8
EXPOSE 8080
VOLUME /tmp
ADD /target/VideoClub-0.0.1-SNAPSHOT.jar VideoClub.jar

RUN bash -c 'touch /VideoClub.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongolocal/products","-jar","/VideoClub.jar"]
