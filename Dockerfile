FROM eclipse-temurin:11-jdk-alpine
COPY target/ordersvc-1.0.jar app.jar
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]