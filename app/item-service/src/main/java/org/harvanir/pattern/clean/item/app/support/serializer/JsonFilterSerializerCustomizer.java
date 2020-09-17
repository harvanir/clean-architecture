package org.harvanir.pattern.clean.item.app.support.serializer;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter.serializeAllExcept;

/** @author Harvan Irsyadi */
public class JsonFilterSerializerCustomizer implements Jackson2ObjectMapperBuilderCustomizer {

  private static final String GLOBAL_SERIALIZE_ALL_EXCEPT = "globalSerializeAllExcept";

  @Override
  public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
    String[] ignoredFields = new String[] {"toBeFiltered"};
    SimpleBeanPropertyFilter serializeAllExcept = serializeAllExcept(ignoredFields);

    jacksonObjectMapperBuilder.filters(
        new SimpleFilterProvider().addFilter(GLOBAL_SERIALIZE_ALL_EXCEPT, serializeAllExcept));
    jacksonObjectMapperBuilder.mixIn(Object.class, GlobalPropertyFilterMixin.class);
  }

  @JsonFilter(GLOBAL_SERIALIZE_ALL_EXCEPT)
  static class GlobalPropertyFilterMixin {}
}
