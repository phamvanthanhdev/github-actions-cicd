FROM openjdk:17
EXPOSE 8080
ADD target/lake-side-hotel.jar lake-side-hotel.jar
ENTRYPOINT ["java","-jar","/lake-side-hotel.jar"]