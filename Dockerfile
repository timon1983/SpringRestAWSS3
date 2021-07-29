FROM openjdk:11
ADD /build/libs/SpringBootRestAWSS3-0.0.1-SNAPSHOT.jar SpringBootRestAWSS3-0.0.1-SNAPSHOT.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","SpringBootRestAWSS3-0.0.1-SNAPSHOT.jar"]