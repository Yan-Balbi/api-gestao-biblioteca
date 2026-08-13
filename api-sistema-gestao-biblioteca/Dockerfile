FROM maven:3.9.11-amazoncorretto-17 AS build

ENTRYPOINT []

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
# Executa os testes unitários durante a criação da imagem.
# Se um teste falhar, o build será interrompido.
RUN mvn clean install
#RUN mvn clean package -DskipTests
#RUN mvn clean package -DskipTests , para pular o testes 

FROM amazoncorretto:17

COPY --from=build /app/target/api-sistema-gestao-biblioteca-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]  