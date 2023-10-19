FROM khipu/openjdk17-alpine:latest
WORKDIR /app
COPY target/productms-0.0.1-SNAPSHOT.jar productApp.jar
CMD ["java", "-jar", "productApp.jar"]
EXPOSE 8082