# Camel based implementation of the _Swagger Petstore_ API

## API Description ##


### Building

    mvn clean package

### Running Locally

    mvn spring-boot:run

Getting the API docs:

    curl http://localhost:8080/openapi.json

## Running on OpenShift

    mvn fabric8:deploy

You can expose the service externally using the following command:

    oc expose svc swagger-petstore

And then you can access it's OpenAPI docs hosted by the service at:

    curl -s http://$(oc get route swagger-petstore --template={{.spec.host}})/openapi.json
