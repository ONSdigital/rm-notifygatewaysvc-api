# Notify Gateway
The Notify Gateway provides an interface for Response Management to send communications to users using the [GOV.UK Notify](https://www.gov.uk/government/publications/govuk-notify/govuk-notify) service. It is implemented using [Spring Boot](http://projects.spring.io/spring-boot/).


##################################################
# To build
##################################################
- Prerequisites:
    - see README (Installation - Maven) at https://github.com/alphagov/notifications-java-client

    - Add this snippet to your Maven settings.xml file (under MAVEN_HOME/conf).
```xml
            <?xml version='1.0' encoding='UTF-8'?>
            <settings xsi:schemaLocation='http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd' xmlns='http://maven.apache.org/SETTINGS/1.0.0' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>
            <profiles>
                <profile>
                    <repositories>
                        <repository>
                            <snapshots>
                                <enabled>true</enabled>
                            </snapshots>
                            <id>artifactory-maven</id>
                            <name>artifactory</name>
                            <url>http://192.168.11.11:8081/artifactory/libs-snapshot-local</url>
                        </repository>
                    </repositories>
                    <pluginRepositories>
                        <pluginRepository>
                            <snapshots>
                              <enabled>true</enabled>
                            </snapshots>
                            <id>artifactory-maven</id>
                            <name>artifactory-plugins</name>
                            <url>http://192.168.11.11:8081/artifactory/libs-snapshot-local</url>
                        </pluginRepository>
                    </pluginRepositories>
                    <id>artifactory</id>
                </profile>
            </profiles>
            <activeProfiles>
                <activeProfile>artifactory</activeProfile>
            </activeProfiles>
            </settings>
```

- mvn clean install -P artifactory-aws


##################################################
# To run the app
##################################################
- Prerequisites:
    - Install RabbitMQ and sudo /sbin/service rabbitmq-server start

- To start:
    - for PROD:
        - mvn spring-boot:run -Drun.profiles=prod
        OR java -jar -Dspring.profiles.active=prod target/notifygatewaysvc-9.35.0-SNAPSHOT.jar

    - for the default profile:
        - mvn spring-boot:run
        OR java -jar target/notifygatewaysvc-9.35.0-SNAPSHOT.jar


- TODO For props config & Consul: create a bootstrap.yml with:
            - under spring.cloud.consul.config:
            enabled: true
            format: FILES
            prefix: configuration
            defaultContext: apps
            profileSeparator: '::'
            - define a git repo for Consul props and point Consul to it
            - start NotifygW and verify props are read from git


## Copyright
Copyright (C) 2016 Crown Copyright (Office for National Statistics)
