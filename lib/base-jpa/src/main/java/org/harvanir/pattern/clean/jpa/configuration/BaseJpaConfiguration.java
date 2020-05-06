package org.harvanir.pattern.clean.jpa.configuration;

import org.harvanir.pattern.clean.jpa.support.JpaEnhancedRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Extend this class as configuration, or copy all the annotation to your configuration class to
 * integrate with autowire IDE.
 *
 * @author Harvan Irsyadi
 */
@EnableJpaRepositories(
    repositoryBaseClass = JpaEnhancedRepository.class,
    basePackages = BaseJpaConstant.JPA_REPOSITORY_PACKAGE)
@EnableJpaAuditing
@EntityScan(BaseJpaConstant.ENTITY_SCAN_PACKAGE)
@ComponentScan(BaseJpaConstant.COMPONENT_SCAN_PACKAGE)
@Configuration(proxyBeanMethods = false)
public class BaseJpaConfiguration {}
