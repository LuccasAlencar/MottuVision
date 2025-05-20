# etapa de build com Maven + Java 17
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app

# copia só as configs do Maven e baixa dependências em cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# copia o código-fonte e gera o JAR
COPY src ./src
RUN mvn clean package -DskipTests -B

# etapa de runtime
FROM eclipse-temurin:17-jdk
WORKDIR /app

# copia o JAR gerado
COPY --from=build /app/target/*.jar app.jar

# expõe a porta da API
EXPOSE 3000

# comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]