package org.harvanir.pattern.clean.item.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @author Harvan Irsyadi */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FindWithDelayRequest {

  private Long id;

  private Integer delaySeconds;
}
