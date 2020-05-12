package org.harvanir.pattern.clean.auditing.support;

import org.springframework.data.auditing.CurrentDateTimeProvider;

import java.time.temporal.TemporalAccessor;
import java.util.Optional;

/** @author Harvan Irsyadi */
public class AuditingHandler {

  private static final CurrentDateTimeProvider dateTimeProvider = CurrentDateTimeProvider.INSTANCE;

  private final AuditingBeanWrapperFactory factory = new AuditingBeanWrapperFactory();

  public void register(Class<?> clazz) {
    factory.populate(clazz);
  }

  public void auditing(Object source) {
    if (source == null) {
      return;
    }

    factory.getAuditableBeanWrapper(source).ifPresent(this::touchTime);
  }

  private void touchTime(AuditableBeanWrapper wrapper) {
    Optional<TemporalAccessor> value = dateTimeProvider.getNow();

    value.ifPresent(
        time -> {
          wrapper.setCreatedTime(time);
          wrapper.setUpdatedTime(time);
        });
  }
}
