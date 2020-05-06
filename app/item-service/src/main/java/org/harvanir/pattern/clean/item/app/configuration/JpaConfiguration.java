package org.harvanir.pattern.clean.item.app.configuration;

import org.harvanir.pattern.clean.jpa.configuration.BaseJpaConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * See #{{@link org.harvanir.pattern.clean.jpa.configuration.BaseJpaConfiguration}}
 *
 * @author Harvan Irsyadi
 */
@Configuration(proxyBeanMethods = false)
public class JpaConfiguration extends BaseJpaConfiguration {}
