FROM gradle:7.6-jdk17 as build
ARG VERSION=latest
WORKDIR /app
COPY . ./
RUN gradle bootJar

#PROD
FROM openjdk:17-slim

ENV SERVICE_PROFILES dummy

RUN apt-get update \
  && apt-get install -y --no-install-recommends curl jq wget \
  && rm -rf /var/lib/apt/lists/*

# Add OpenTelemetry instrumenation lib
RUN wget --no-check-certificate https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar

COPY --from=build /app/build/libs/*.jar /app.jar

EXPOSE 8765

ENTRYPOINT ["java", "-javaagent:opentelemetry-javaagent.jar", "-jar", "-Dspring.profiles.active=${SERVICE_PROFILES}", "/app.jar"]

HEALTHCHECK --start-period=30s --interval=30s --timeout=3s --retries=3 \
  CMD curl --silent --fail --request GET http://localhost:8765/actuator/health \
  | jq --exit-status '.status == "UP"' || exit 1
