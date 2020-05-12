package org.harvanir.pattern.clean.item.app.configuration;

import org.harvanir.pattern.clean.auditing.configuration.EnableR2dbcAuditing;
import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.ProviderMarker;
import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.model.EntityMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/** @author Harvan Irsyadi */
@EnableR2dbcAuditing(basePackageClasses = EntityMarker.class)
@EnableR2dbcRepositories(basePackageClasses = ProviderMarker.class)
@ComponentScan(basePackageClasses = ProviderMarker.class)
@Configuration(proxyBeanMethods = false)
public class RepositoryConfiguration {}
