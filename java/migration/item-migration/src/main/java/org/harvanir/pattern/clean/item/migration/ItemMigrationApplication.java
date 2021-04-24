package org.harvanir.pattern.clean.item.migration;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

/** @author Harvan Irsyadi */
@Import({DataSourceAutoConfiguration.class, FlywayAutoConfiguration.class})
public class ItemMigrationApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(ItemMigrationApplication.class)
        .web(WebApplicationType.NONE)
        .build()
        .run(args);
  }
}
