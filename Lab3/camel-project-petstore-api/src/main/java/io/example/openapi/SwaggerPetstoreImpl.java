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
