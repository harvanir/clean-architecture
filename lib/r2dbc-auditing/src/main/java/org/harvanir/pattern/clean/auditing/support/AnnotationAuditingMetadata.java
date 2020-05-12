package org.harvanir.pattern.clean.auditing.support;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

/** @author Harvan Irsyadi */
public class AnnotationAuditingMetadata {

  private static final Map<Class<?>, AnnotationAuditingMetadata> CACHE =
      new ConcurrentReferenceHashMap<>();

  private Field createdTimeField;

  private Field updatedTimeField;

  public static void populate(Class<?> clazz) {
    AnnotationAuditingMetadata auditingMetadata = new AnnotationAuditingMetadata();
    auditingMetadata.initialize(clazz);
  }

  public static AnnotationAuditingMetadata getMetadata(Class<?> clazz) {
    return CACHE.get(clazz);
  }

  private void initialize(Class<?> clazz) {
    for (Field field : clazz.getDeclaredFields()) {
      if (field.getAnnotation(CreatedDate.class) != null) {
        ReflectionUtils.makeAccessible(field);
        createdTimeField = field;
        continue;
      }

      if (field.getAnnotation(LastModifiedDate.class) != null) {
        ReflectionUtils.makeAccessible(field);
        updatedTimeField = field;
      }
    }

    if (isAuditable()) {
      CACHE.putIfAbsent(clazz, this);
    }
  }

  public boolean isAuditable() {
    return createdTimeField != null || updatedTimeField != null;
  }

  public Field getCreatedTimeField() {
    return createdTimeField;
  }

  public Field getUpdatedTimeField() {
    return updatedTimeField;
  }
}
