 SpringRestAWSS3
 
 The REST API interacts with the AWS S3 file storage and provides the ability to access files and download history.
 Interaction with S3 is implemented using the AWS SDK.
 The download history is stored in MYSQL.
 
 A JWT token is used to access the work.(Spring Security)
 
 Access levels:

ADMIN - full access to the application({"email":"admin@gmail.com","password":"admin"})

MODERATOR - adding and deleting files({"email":"moder@mail.ru","password":"moder"})

USER - read only all data except User({"email":"user@yandex.ru","password":"user"})

 Spring Boot on Docker connecting to MySQL Docker container

1.Use MySQL Image published by Docker Hub (https://hub.docker.com/_/mysql/) Command to run the mysql container: docker run -d -p 6033:3306 --name=mysql-docker --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=mysqldocker" mysql

2.In the Spring Boot Application, use the same container name of the mysql instance in the application.properties: spring.datasource.url=jdbc:mysql://mysql-docker:3306/mysqldocker

3.Create a Dockerfile for creating a docker image from the Spring Boot Application: FROM openjdk:11 ADD /build/libs/SpringBootRestAWSS3-0.0.1-SNAPSHOT.jar SpringBootRestAWSS3-0.0.1-SNAPSHOT.jar EXPOSE 8086 ENTRYPOINT ["java","-jar","SpringBootRestAWSS3-0.0.1-SNAPSHOT.jar"]

4.Using the Dockerfile create the Docker image. From the directory of Dockerfile: docker build . -t spring_rest_project_app

5.Run the Docker image spring_rest_project_app and connect with mysql-docker: docker run -p8086:8086 --name spring_rest_project_app --link mysql-docker:mysql -d spring_rest_project_app
