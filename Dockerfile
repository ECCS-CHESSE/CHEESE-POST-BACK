# Etapa de construcción
FROM amazoncorretto:25 AS build

# Instala Maven
RUN yum install -y maven

COPY pom.xml /app/
COPY src /app/src/
WORKDIR /app
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM amazoncorretto:25

# Instalar fuentes y dependencias para JasperReports
RUN yum install -y \
    fontconfig \
    wget \
    cabextract \
    && yum clean all

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
ENTRYPOINT ["java", "-Djava.awt.headless=true", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]