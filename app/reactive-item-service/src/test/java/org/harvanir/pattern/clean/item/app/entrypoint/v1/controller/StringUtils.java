package org.harvanir.pattern.clean.item.app.entrypoint.v1.controller;

/** @author Harvan Irsyadi */
public class StringUtils {

  private StringUtils() {}

  static String padZeroRight(String value, int maxLength) {
    return value.length() < maxLength
        ? String.format("%s%0" + (maxLength - value.length()) + "d", value, 0)
        : value;
  }
}
