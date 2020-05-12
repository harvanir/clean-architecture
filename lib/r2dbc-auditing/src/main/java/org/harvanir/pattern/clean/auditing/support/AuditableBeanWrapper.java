package org.harvanir.pattern.clean.auditing.support;

import java.time.temporal.TemporalAccessor;

/** @author Harvan Irsyadi */
public interface AuditableBeanWrapper {

  void setCreatedTime(TemporalAccessor time);

  void setUpdatedTime(TemporalAccessor time);
}
