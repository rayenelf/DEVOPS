# Utilise une image JDK pour Spring Boot
FROM eclipse-temurin:17-jdk-jammy

# Copie le jar généré par Maven
ARG JAR_FILE=target/student-management-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Expose le port de l’application
EXPOSE 8080

# Commande pour lancer l’application
ENTRYPOINT ["java","-jar","/app.jar"]
