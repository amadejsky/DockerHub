# Użyj oficjalnej wersji JDK jako podstawy
FROM maven:3.8.5-openjdk-17 AS build

# Ustaw katalog roboczy
WORKDIR /app

# Skopiuj plik pom.xml i wszystkie zależności (aby buforować warstwy Maven)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
COPY mvnw.cmd .
RUN ./mvnw dependency:go-offline

# Skopiuj całą resztę projektu
COPY src ./src

# Zbuduj aplikację
RUN ./mvnw package -DskipTests

# Użyj oficjalnego obrazu JDK jako bazy do uruchomienia aplikacji
FROM openjdk:17-jdk-alpine

# Ustaw katalog roboczy
WORKDIR /app

# Skopiuj skompilowany plik JAR z poprzedniego etapu budowy
COPY --from=build /app/target/*.jar app.jar

# Wystaw port aplikacji (np. 8080)
EXPOSE 8080

# Uruchom aplikację
ENTRYPOINT ["java", "-jar", "app.jar"]
