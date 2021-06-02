# Fuse on OpenShift - Practical Labs

## Lab2: Connect Camel with Kafka

### Summary
This lab shows a way to connect two camel routes using a Kafka Topic.

### Step 1: Create a new project in the OpenShift cluster
Create a new project called $user-lab2

```
$ oc new-project $user-lab2
```

### Step 2: Deploy the KafkaProducer & KafkaConsumer
Open two cli instances and start the *KafkaProducer* and *KafkaConsumer* routes

```
oc create secret generic kafka-props --from-file application.properties
kamel run --secret kafka-props KafkaProducer.java --dev
```

```
kamel run --secret kafka-props KafkaConsumer.java --dev
```

> The parameter *--dev* provides detailed build information and log information of the running container

> Detailed Kafka Connections Information can be found in the application.properties file which is provided to the container as secret.
