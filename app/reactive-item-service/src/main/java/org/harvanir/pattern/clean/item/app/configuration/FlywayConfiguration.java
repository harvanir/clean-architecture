package org.harvanir.pattern.clean.item.app.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author Harvan Irsyadi */
@EnableConfigurationProperties(FlywayProperties.class)
@Configuration(proxyBeanMethods = false)
public class FlywayConfiguration {

  @Bean(initMethod = "migrate")
  public Flyway flyway(FlywayProperties flywayProperties) {
    return new Flyway(
        Flyway.configure()
            .baselineOnMigrate(flywayProperties.isBaselineOnMigrate())
            .dataSource(
                flywayProperties.getUrl(),
                flywayProperties.getUser(),
                flywayProperties.getPassword()));
  }
}
