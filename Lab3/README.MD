# Fuse on OpenShift - Practical Labs

## Lab3: Development Lifecycle with Apicurio

### Summary
This lab demonstrates a REST service implementation in an API first manner.

### Step 1: Create a new project in the OpenShift cluster
Create a new project called $user-lab3

### Step 2: Import / Design Api
Design or import the [Petstore REST API](petstore.json) in Apicurio

### Step 3: Download skeleton projects
Click on *Generate* > *Fuse Camel Project* and Download the resulting zip file.

### Step 4: Browse & adjust project skeleton
1. Browse the project skeleton
> The project skeleton reflects the openapi specification. However it is quite static, so the generation via Apicurio would be necessary after every change.
>
> The next steps implment a more flexible approach based on the project skeleton, where the gluecode is generated on every build
1. Add required plugins in pom.xml
	```
	<plugin>
		<groupId>io.swagger.codegen.v3</groupId>
		<artifactId>swagger-codegen-maven-plugin</artifactId>
		<version>3.0.19</version>
		<executions>
			<execution>
				<goals>
						<goal>generate</goal>
				</goals>
				<configuration>
						<inputSpec>${project.basedir}/src/main/resources/openapi.json</inputSpec>
						<language>java</language>
						<output>${project.basedir}/src/main/generated</output>
						<configOptions>
							 <sourceFolder>/</sourceFolder>
						</configOptions>
						<generateApis>false</generateApis>
						<generateModelTests>false</generateModelTests>
						<generateModelDocumentation>false</generateModelDocumentation>
						<generateSupportingFiles>false</generateSupportingFiles>
				</configuration>
			</execution>
		</executions>
	</plugin>

	<plugin>
		<groupId>org.apache.camel</groupId>
		<artifactId>camel-restdsl-openapi-plugin</artifactId>
		<version>3.10.0</version>
		<executions>
			<execution>
				<goals>
					<goal>generate</goal>
				</goals>
			</execution>
		</executions>
		<configuration>
			<specificationUri>${project.basedir}/src/main/resources/openapi.json</specificationUri>
			<outputDirectory>${project.basedir}/src/main/generated</outputDirectory>
		</configuration>
	</plugin>

	<plugin>
		<groupId>org.codehaus.mojo</groupId>
		<artifactId>build-helper-maven-plugin</artifactId>
		<version>3.2.0</version>
		<executions>
			<execution>
				<id>add-source</id>
				<phase>generate-sources</phase>
				<goals>
					<goal>add-source</goal>
				</goals>
				<configuration>
					<sources>
						<source>${project.basedir}/src/main/generated</source>
					</sources>
				</configuration>
			</execution>
		</executions>
	</plugin>
	```
1. Add required dependencies in pom.xml
	```
  <dependency>
		<groupId>com.google.code.gson</groupId>
		<artifactId>gson</artifactId>
	</dependency>
	<dependency>
	    <groupId>io.swagger.core.v3</groupId>
	    <artifactId>swagger-annotations</artifactId>
	    <version>2.1.9</version>
	</dependency>
	<dependency>
		<groupId>org.apache.camel</groupId>
		<artifactId>camel-gson</artifactId>
  </dependency>
	```
1. Delete the file camel-context.xml
 1. Remove the reference *@ImportResource({"classpath:spring/camel-context.xml"})* in *Application.java*
1. Add the Service implementation to *src/main/java/io/example/openapi/SwaggerPetstoreImpl.java*
	```
	package io.example.openapi;

	import java.util.HashMap;
	import java.util.Map;

	import org.apache.camel.Exchange;
	import org.apache.camel.Processor;
	import org.apache.camel.builder.RouteBuilder;
	import org.apache.camel.model.dataformat.JsonLibrary;
	import org.apache.camel.model.rest.RestParamType;
	import org.springframework.stereotype.Component;
	import io.example.openapi.model.Pet;
	import io.example.openapi.model.Pets;

	@Component
	public final class SwaggerPetstoreImpl extends RouteBuilder {
	    public static Map<Integer, Pet> pets;

	    {
	      pets = new HashMap<>();

	      Pet mike = new Pet();
	      mike.setId(1l);
	      mike.setName("mike");
	      pets.put(1, mike);

	      Pet donald = new Pet();
	      donald.setId(2l);
	      donald.setName("donald");
	      pets.put(2, donald);
	    }

	    public void configure() {
	        from("direct:listPets")
	          .log(">>> direct:listPets")
	          .process(new Processor(){
	            @Override
	            public void process(Exchange exchange) throws Exception {
	              Pets pets = new Pets();
	              pets.addAll(SwaggerPetstoreImpl.pets.values());
	              exchange.getIn().setBody(pets);
	            }
	          })
	          .marshal().json(JsonLibrary.Gson);

	        from("direct:createPets")
	          .log(">>> direct:createPets")
	          .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
	          .transform(simple("Not yet implemented"));

	        from("direct:showPetById")
	          .log(">>> direct:showPetById")
	          .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
	          .transform(simple("Not yet implemented"));
	    }
	}
	```
1. Add the project property to disable health checks:
	```
	<fabric8.skipHealthCheck>true</fabric8.skipHealthCheck>
	```

### Step 5: Run the project locally
Run the project locally using *mvn spring-boot:run*

You can reach the API using this URL: http://localhost:8080/v1/pets

### Step 6: Deploy the project to OpenShift
Deploy the project to OpenShift using *mvn fabric8:deploy*
