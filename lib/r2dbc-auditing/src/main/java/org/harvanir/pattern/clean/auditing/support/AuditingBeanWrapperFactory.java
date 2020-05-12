package org.harvanir.pattern.clean.auditing.support;

import java.util.Optional;

/** @author Harvan Irsyadi */
public class AuditingBeanWrapperFactory {

  public void populate(Class<?> clazz) {
    AnnotationAuditingMetadata.populate(clazz);
  }

  public Optional<AuditableBeanWrapper> getAuditableBeanWrapper(Object source) {
    AnnotationAuditingMetadata auditingBean =
        AnnotationAuditingMetadata.getMetadata(source.getClass());

    if (auditingBean != null) {
      return Optional.of(new ReflectionAuditingBeanWrapper(source, auditingBean));
    }

    return Optional.empty();
  }
}
