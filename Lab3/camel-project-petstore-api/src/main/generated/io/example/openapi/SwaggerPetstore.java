package io.example.openapi;

import javax.annotation.Generated;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

/**
 * Generated from OpenApi specification by Camel REST DSL generator.
 */
@Generated("org.apache.camel.generator.openapi.PathGenerator")
@Component
public final class SwaggerPetstore extends RouteBuilder {
    /**
     * Defines Apache Camel routes using REST DSL fluent API.
     */
    public void configure() {

        restConfiguration().component("servlet").contextPath("/");

        rest("/v1")
            .get("/pets")
                .id("listPets")
                .produces("application/json")
                .param()
                    .name("limit")
                    .type(RestParamType.query)
                    .dataType("integer")
                    .required(false)
                    .description("How many items to return at one time (max 100)")
                .endParam()
                .to("direct:listPets")
            .post("/pets")
                .id("createPets")
                .produces("application/json")
                .to("direct:createPets")
            .get("/pets/{petId}")
                .id("showPetById")
                .produces("application/json")
                .param()
                    .name("petId")
                    .type(RestParamType.path)
                    .dataType("string")
                    .required(true)
                    .description("The id of the pet to retrieve")
                .endParam()
                .to("direct:showPetById");
    }
}
