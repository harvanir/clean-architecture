package org.harvanir.pattern.clean.auditing.support;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/** @author Harvan Irsyadi */
public class ReflectionAuditingBeanWrapper implements AuditableBeanWrapper {

  private final Object source;

  private final AnnotationAuditingMetadata metadata;

  public ReflectionAuditingBeanWrapper(Object source, AnnotationAuditingMetadata metadata) {
    this.source = source;
    this.metadata = metadata;
  }

  @Override
  public void setCreatedTime(TemporalAccessor time) {
    Field field = metadata.getCreatedTimeField();

    if (field != null && ReflectionUtils.getField(field, source) == null) {
      ReflectionUtils.setField(field, source, getFinalTime(field.getType(), time));
    }
  }

  @Override
  public void setUpdatedTime(TemporalAccessor time) {
    Field field = metadata.getUpdatedTimeField();

    if (field != null) {
      ReflectionUtils.setField(field, source, getFinalTime(field.getType(), time));
    }
  }

  private Object getFinalTime(Class<?> target, TemporalAccessor time) {
    if (TemporalAccessor.class.isAssignableFrom(target)) {
      return time;
    }
    if (Date.class.equals(target) && time instanceof LocalDateTime) {
      return Date.from(((LocalDateTime) time).atZone(ZoneId.systemDefault()).toInstant());
    }

    return null;
  }
}
