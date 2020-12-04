package org.harvanir.pattern.clean.item.core.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ConfigurationProperties(prefix = "app")
public class AppProperties {

  private Retry retry = new Retry();

  @Getter
  @Setter
  public static class Retry {

    private long maxAttempts = 1000;

    private long delay = 1;

    private Set<String> onExceptions = new HashSet<>();
  }
}
