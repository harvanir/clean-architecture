package org.harvanir.pattern.clean.item.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** @author Harvan Irsyadi */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemIncreaseRequest {

  private Long id;

  private int increment;
}
