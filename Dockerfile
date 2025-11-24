# Utilise une image JDK pour Spring Boot
FROM openjdk:17-jdk-slim

# Copie le jar généré par Maven
ARG JAR_FILE=target/student-management-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Expose le port de ton application Spring Boot
EXPOSE 8080

# Commande pour lancer l’application
ENTRYPOINT ["java","-jar","/app.jar"]
