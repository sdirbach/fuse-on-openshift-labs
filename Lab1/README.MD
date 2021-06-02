# Fuse on OpenShift - Practical Labs

## Lab1: Camel K with Knative

### Summary
This lab is intended to express the Camel K capabilities in connection with a Knative enabled cluster. This includes automatic scaling functionalities, as well as message based eventing features.

### Step 1: Create a new project in the OpenShift cluster
Create a new project called $user-lab1

```
$ oc new-project $user-lab1
```

### Step 2: Run the *SimpleRestDSL* Route
Open the Pod overview in the Web Console or open a terminal and watch the pod creation:

```
$ oc get pods -w
```

Open another terminal and run the *SimpleRestDSL.java* Route.

```
$ kamel run SimpleRestDSL.java
```

> This example demonstrates the Camel K Serverless Autoscaling Features.
> At first you will see a integration-kit-container which builds the container image that hosts the camel context. After the build the container image will start and serve requests. After a defined idle time without requests (*stable window*; Default 60s) the container will shut down. When a new request comes in, Knative will start a new container which will serve the request.

> Auto-scaling is a Knative Service Feature that is used, as the Camel K Operator evaluates that Knative is installed and creates a Knative service. You can list and inspect the created Knative Services using these commands:
>
> *kn service list*
>
> *kn service describe \<service-name\>*

### Step 3: Run the *RestToEventDSL* & *EventToLogDSL* Route

Import the *Camel channel* to OpenShift, enable Knative Eventing Injection and run the *RestToEventDSL.java* & *EventToLogDSL.java* routes.

```
$ oc create -f camel-channel.yaml
$ oc label namespace $user-lab1 knative-eventing-injection=enabled
$ kamel run RestToEventDSL.java
$ kamel run EventToLogDSL.java
```

> This example behaves in the same way as the previous example. However instead of using the direct Channel it uses the camel channel through Knative Eventing which uses Cloud events (https://cloudevents.io) as message format (Wrapping the Camel Exchanee)
