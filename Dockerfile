# Etapa de construcción
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiar el .jar generado
COPY --from=build /app/target/*.jar app.jar

# Copiar el Oracle Wallet dentro del contenedor
COPY src/main/resources/Wallet_BDFullStack3 /app/wallet

# Exponer el puerto que usa el microservicio
EXPOSE 8081

# Ejecutar el .jar con perfil docker
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=docker $JAVA_OPTS -jar app.jar"]
