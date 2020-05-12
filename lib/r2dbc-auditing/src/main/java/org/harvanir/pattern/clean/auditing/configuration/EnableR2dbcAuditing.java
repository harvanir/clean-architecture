package org.harvanir.pattern.clean.auditing.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** @author Harvan Irsyadi */
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(R2dbcAuditingRegistrar.class)
public @interface EnableR2dbcAuditing {

  Class<?>[] basePackageClasses() default {};
}
