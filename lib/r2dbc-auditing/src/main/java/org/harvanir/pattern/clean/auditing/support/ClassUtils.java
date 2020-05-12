package org.harvanir.pattern.clean.auditing.support;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/** @author Harvan Irsyadi */
public class ClassUtils {

  private ClassUtils() {}

  public static List<Class<?>> getClassByPackage(String basePackage) {
    ClassPathScanningCandidateComponentProvider scanner =
        new ClassPathScanningCandidateComponentProvider(false);
    scanner.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));

    List<Class<?>> result = new ArrayList<>();
    for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
      Class<?> clazz = loadClass(bd.getBeanClassName());

      if (clazz != null && AnnotationUtils.findAnnotation(clazz, Table.class) != null) {
        result.add(clazz);
      }
    }

    return result;
  }

  private static Class<?> loadClass(String name) {
    try {
      return Objects.requireNonNull(org.springframework.util.ClassUtils.getDefaultClassLoader())
          .loadClass(name);
    } catch (ClassNotFoundException e) {
      e.printStackTrace(); // NOSONAR
    }

    return null;
  }
}
