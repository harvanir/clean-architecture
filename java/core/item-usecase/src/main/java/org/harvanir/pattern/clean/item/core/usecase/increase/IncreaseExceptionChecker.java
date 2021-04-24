package org.harvanir.pattern.clean.item.core.usecase.increase;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/** @author Harvan Irsyadi */
@Slf4j
public class IncreaseExceptionChecker {

  private final Set<String> onExceptions;

  public IncreaseExceptionChecker(Set<String> onExceptions) {
    this.onExceptions = onExceptions;
  }

  public boolean shouldRetry(Throwable t) {
    String className = t.getClass().getName();
    boolean contains = onExceptions.contains(className);

    if (log.isDebugEnabled()) {
      log.debug("Contain exception '{}': {}", className, contains);
    }

    return onExceptions.contains(className);
  }
}
