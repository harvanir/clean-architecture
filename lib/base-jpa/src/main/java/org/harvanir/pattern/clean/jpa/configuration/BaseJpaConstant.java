package org.harvanir.pattern.clean.jpa.configuration;

/** @author Harvan Irsyadi */
public class BaseJpaConstant {

  public static final String JPA_REPOSITORY_PACKAGE =
      "org.harvanir.pattern.clean.*.provider.gateway.jpa.repository";

  public static final String COMPONENT_SCAN_PACKAGE =
      "org.harvanir.pattern.clean.*.provider.gateway.jpa";

  public static final String ENTITY_SCAN_PACKAGE =
      "org.harvanir.pattern.clean.*.provider.gateway.jpa.model";

  private BaseJpaConstant() {}
}
