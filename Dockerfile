FROM openjdk:17-jdk-slim

# Define o diretório de trabalho no container
WORKDIR /app

COPY target/seu-projeto.jar app.jar

EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
