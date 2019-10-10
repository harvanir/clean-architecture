package org.harvanir.pattern.clean.configuration;

import org.harvanir.pattern.clean.configuration.properties.AppProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(AppProperties.class)
@Configuration
public class AppConfiguration {}
