package org.harvanir.pattern.clean.item.app;

import org.harvanir.pattern.clean.item.app.configuration.CoreConfiguration;
import org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter.BeanMapperImpl;
import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.GatewayBeanMapperImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/** @author Harvan Irsyadi */
@Import({CoreConfiguration.class, GatewayBeanMapperImpl.class, BeanMapperImpl.class})
@Configuration(proxyBeanMethods = false)
public class BaseTestConfiguration {}
