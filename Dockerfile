# =========================
# 1️⃣ Build Stage
# =========================
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Gradle 캐시 활용을 위해 먼저 설정 파일 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon || true

# 소스 복사 후 빌드
COPY src src
RUN ./gradlew bootJar --no-daemon

# =========================
# 2️⃣ Runtime Stage
# =========================
FROM eclipse-temurin:21-jre

WORKDIR /app

# 빌드된 jar만 복사
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]