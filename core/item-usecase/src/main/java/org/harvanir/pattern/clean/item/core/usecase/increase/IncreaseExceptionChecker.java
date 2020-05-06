package org.harvanir.pattern.clean.item.core.usecase.increase;

/** @author Harvan Irsyadi */
public class IncreaseExceptionChecker {

  public boolean shouldRetry(Throwable t) {
    String className = t.getClass().getName();

    return "org.hibernate.StaleObjectStateException".equals(className)
        || "org.springframework.orm.ObjectOptimisticLockingFailureException".equals(className);
  }
}
