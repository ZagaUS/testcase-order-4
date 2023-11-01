FROM registry.access.redhat.com/ubi8/openjdk-11:1.14
EXPOSE 5089
RUN mkdir -p /opt/app
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} /opt/app/app.jar

ENV POSTGRES_URL=jdbc:postgresql://dev-postgres-primary.postgresql-workspace.svc:5432/observability-demo-tables
ENV POSTGRES_USER=observability-demo-user
ENV POSTGRES_PASSWORD=observability123

ENV OTEL_EXPORTER=otlp
ENV OTEL_EXPORTER_ENDPOINT=http://observai-main-otel.apps.zagaopenshift.zagaopensource.com:9112
ENV OTEL_SERVICE_NAME=order_project-vm1

COPY ./javaagent.jar /opt/app
COPY ./instrumented-app.sh /opt/app

WORKDIR /opt/app/

ENTRYPOINT ["/bin/bash", "-c", "./instrumented-app.sh"]
