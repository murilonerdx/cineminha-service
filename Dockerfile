# Use uma imagem base oficial do OpenJDK
FROM openjdk:17-jdk-slim

# O diretório onde a aplicação será copiada dentro do container
WORKDIR /app

# Copia o JAR gerado do build do Maven para dentro do container
COPY target/cineminha-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que o Spring Boot vai rodar
EXPOSE 8080

# Comando para rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
