package org.harvanir.pattern.clean.item.app.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@EnableConfigurationProperties(AppProperties.class)
@Configuration(proxyBeanMethods = false)
public class AppConfiguration {}
