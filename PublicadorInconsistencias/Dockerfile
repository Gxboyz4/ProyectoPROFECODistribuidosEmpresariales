FROM openjdk:21-jdk-slim

COPY target/PublicadorInconsistencias.jar /app/CapaDatosProductos.jar

WORKDIR /app

CMD ["java", "-jar", "CapaDatosProductos.jar"]