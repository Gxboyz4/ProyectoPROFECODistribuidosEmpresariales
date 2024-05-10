FROM openjdk:21-jdk-slim

COPY target/CapaDatosInconsistencias-1.0-SNAPSHOT.jar /app/CapaDatosInconsistencias.jar

WORKDIR /app

CMD ["java", "-jar", "CapaDatosInconsistencias.jar"]