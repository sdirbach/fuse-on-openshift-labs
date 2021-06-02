# Fuse on OpenShift - Practical Labs

## Prequisites
### Runtime
To run these labs you need an OpenShift cluster with these Operators Installed & Configured:
- Red Hat Integration - Camel K
- Red Hat OpenShfit Serverless Oparator
 - Configured with a Knative Serving & Knative Eventing Operands
- Red Hat Integration AMQ Streams
 - Configured with a Kafka Operand named *amq-streams* in the *amq-streams* project

### Access
The OpenShift cluster is set up with 5 Users called *dev1* to *dev5*. The password for each user is *openshift*.

Each user has normal access rights and can create new projects. Furthermore the users have full read access to the public project *amq-streams* which hosts a AMQ Streams Kafka cluster.

## Lab Overview
1. [Lab0: Set-up Client Environment](Lab0/README.MD)
1. [Lab1: Camel K with Knative](Lab1/README.MD)
1. [Lab2: Connect Camel with Kafka](Lab2/README.MD)
1. [Lab3: Development Lifecycle with Apicurio](Lab3/README.MD)
