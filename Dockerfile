FROM openjdk:11-jdk-oracle

COPY target/api*.war /app.war

CMD ["/usr/bin/java", "-jar", "/app.war"]