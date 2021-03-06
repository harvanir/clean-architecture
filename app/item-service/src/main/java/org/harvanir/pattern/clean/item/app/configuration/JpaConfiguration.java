package org.harvanir.pattern.clean.item.app.configuration;

import org.harvanir.pattern.clean.jpa.configuration.BaseJpaConfiguration;
import org.harvanir.pattern.clean.jpa.configuration.BaseJpaConstant;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * See #{{@link org.harvanir.pattern.clean.jpa.configuration.BaseJpaConfiguration}}
 *
 * @author Harvan Irsyadi
 */
@ComponentScan(BaseJpaConstant.COMPONENT_SCAN_PACKAGE)
@Configuration(proxyBeanMethods = false)
public class JpaConfiguration extends BaseJpaConfiguration {}
