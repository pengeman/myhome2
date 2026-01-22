FROM maven:3.6.1-jdk-8-alpine AS builder
WORKDIR /build

# 创建Maven配置目录
RUN mkdir -p /root/.m2

# 复制自定义settings.xml到Maven配置目录
COPY settings.xml /root/.m2/settings.xml

ADD pom.xml .
RUN mvn verify clean --fail-never

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
