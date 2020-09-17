package org.harvanir.pattern.clean.item.app.configuration;

import org.harvanir.pattern.clean.item.app.support.serializer.JsonFilterSerializerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** @author Harvan Irsyadi */
@Configuration(proxyBeanMethods = false)
public class WebMvcConfiguration implements WebMvcConfigurer {

  @Bean
  public JsonFilterSerializerCustomizer jsonFilterSerializerCustomizer() {
    return new JsonFilterSerializerCustomizer();
  }
}
