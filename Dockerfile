FROM quay.io/wildfly/wildfly:31.0.1.Final-jdk17
LABEL authors="Tshidiso"
COPY /target/demoing.war /opt/jboss/wildfly/standalone/deployments/