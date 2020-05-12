package org.harvanir.pattern.clean.auditing.configuration;

import org.harvanir.pattern.clean.auditing.support.R2dbcAuditingAspect;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/** @author Harvan Irsyadi */
class R2dbcAuditingRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(
      AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
    Class<?>[] clazz = getBasePackageClasses(annotationMetadata);

    if (clazz == null || clazz.length == 0) {
      return;
    }

    // register aspect bean
    RootBeanDefinition beanDefinition = new RootBeanDefinition();
    beanDefinition.setBeanClass(R2dbcAuditingAspect.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

    ConstructorArgumentValues argumentValues = beanDefinition.getConstructorArgumentValues();
    argumentValues.addGenericArgumentValue(clazz);
    beanDefinition.setConstructorArgumentValues(argumentValues);

    String beanName =
        StringUtils.uncapitalize(ClassUtils.getShortName(beanDefinition.getBeanClass()));
    registry.registerBeanDefinition(beanName, beanDefinition);
  }

  private Class<?>[] getBasePackageClasses(AnnotationMetadata annotationMetadata) {
    Map<String, Object> attributes =
        annotationMetadata.getAnnotationAttributes(EnableR2dbcAuditing.class.getName());

    if (attributes == null) {
      return new Class[0];
    }

    Object basePackageClasses = attributes.get("basePackageClasses");
    Class<?>[] clazz = null;

    if (basePackageClasses != null) {
      clazz = (Class<?>[]) basePackageClasses;
    }

    return clazz;
  }
}
