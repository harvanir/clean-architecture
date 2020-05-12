package org.harvanir.pattern.clean.auditing.support;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/** @author Harvan Irsyadi */
@Slf4j
@Setter
@Aspect
public class R2dbcAuditingAspect {

  private AuditingHandler auditingHandler;

  public R2dbcAuditingAspect(Class<?>[] basePackageClasses) {
    initialize(basePackageClasses);
  }

  private void initialize(Class<?>[] basePackageClasses) {
    register(loadClasses(basePackageClasses));
  }

  private List<Class<?>> loadClasses(Class<?>[] basePackageClasses) {
    if (log.isDebugEnabled()) {
      log.debug("Load classes from: {}", Arrays.toString(basePackageClasses));
    }

    ArrayList<Class<?>> classes = new ArrayList<>();
    Arrays.stream(basePackageClasses)
        .forEach(
            aClass -> classes.addAll(ClassUtils.getClassByPackage(aClass.getPackage().getName())));

    return classes;
  }

  private void register(List<Class<?>> classes) {
    auditingHandler = new AuditingHandler();

    classes.forEach(auditingHandler::register);
  }

  @Around(
      "execution(* org.springframework.data.repository.reactive.ReactiveCrudRepository.save*(..))")
  Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    log.debug("Intercepting: {}", joinPoint.getSignature());

    for (int i = 0; i < joinPoint.getArgs().length; i++) {
      Object o = joinPoint.getArgs()[i];

      if (o instanceof Iterable) {
        iterate(o, auditingHandler::auditing);
      } else if (o instanceof Flux) {
        joinPoint.getArgs()[i] = getFlux(o);
      } else if (o instanceof Mono) {
        joinPoint.getArgs()[i] = getMono(o);
      } else {
        auditingHandler.auditing(o);
      }
    }

    return joinPoint.proceed(joinPoint.getArgs());
  }

  private void iterate(Object object, Consumer<Object> consumer) {
    for (Object o : (Iterable<?>) object) {
      consumer.accept(o);
    }
  }

  private Flux<?> getFlux(Object o) {
    return ((Flux<?>) o).doOnNext(auditingHandler::auditing);
  }

  private Mono<?> getMono(Object o) {
    return ((Mono<?>) o).doOnNext(auditingHandler::auditing);
  }
}
