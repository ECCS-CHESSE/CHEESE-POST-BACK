# Etapa de construcción
FROM eclipse-temurin:25-jdk AS build

# Instala Maven
RUN apt-get update && apt-get install -y maven

COPY pom.xml /app/
COPY src /app/src/
WORKDIR /app
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:25-jre

# Instalar fuentes y dependencias para JasperReports
RUN apt-get update && apt-get install -y \
    fontconfig \
    fonts-dejavu-core \
    fonts-liberation \
    fonts-liberation2 \
    fonts-freefont-ttf \
    wget \
    cabextract \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Descargar e instalar fuentes Microsoft manualmente
RUN mkdir -p /usr/share/fonts/truetype/msttcorefonts \
    && cd /tmp \
    && wget -q https://downloads.sourceforge.net/corefonts/times32.exe \
    && cabextract -d /usr/share/fonts/truetype/msttcorefonts times32.exe \
    && wget -q https://downloads.sourceforge.net/corefonts/arial32.exe \
    && cabextract -d /usr/share/fonts/truetype/msttcorefonts arial32.exe \
    && rm -f /tmp/*.exe \
    && fc-cache -fv

# Variables de entorno para JasperReports
ENV JAVA_OPTS="-Djava.awt.headless=true -Dfile.encoding=UTF-8"

COPY --from=build /app/target/*.jar app.jar
RUN useradd runtime
USER runtime
ENTRYPOINT ["java", "-Djava.awt.headless=true", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]