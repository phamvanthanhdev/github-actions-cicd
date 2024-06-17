FROM openjdk:8
EXPOSE 8080
ADD target/lake-side-hotel.jar lake-side-hotel.jar
ENTRYPOINT ["java","-jar","/lake-side-hotel.jar"]