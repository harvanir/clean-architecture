package org.harvanir.pattern.clean.app.base.configuration;

import org.harvanir.pattern.clean.app.base.driver.jpa.support.JpaEnhancedRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/** @author Harvan Irsyadi */
@EnableJpaRepositories(
    repositoryBaseClass = JpaEnhancedRepository.class,
    basePackages = "org.harvanir.pattern.clean.app.item.driver.jpa.repository")
@EnableJpaAuditing
@Configuration
public class JpaConfiguration {}
