FROM gradle:7.6-jdk17 as build
ARG VERSION=latest
WORKDIR /app
COPY . ./
RUN gradle bootJar

#PROD
FROM openjdk:17-slim

ENV SERVICE_PROFILES dummy

RUN apt-get update \
  && apt-get install -y --no-install-recommends curl jq \
  && rm -rf /var/lib/apt/lists/*

COPY --from=build /app/build/libs/*.jar /app.jar

EXPOSE 8765

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${SERVICE_PROFILES}", "/app.jar"]

HEALTHCHECK --start-period=30s --interval=30s --timeout=3s --retries=3 \
  CMD curl --silent --fail --request GET http://localhost:8765/actuator/health \
  | jq --exit-status '.status == "UP"' || exit 1
