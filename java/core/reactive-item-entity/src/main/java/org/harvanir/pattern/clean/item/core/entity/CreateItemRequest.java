package org.harvanir.pattern.clean.item.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/** @author Harvan Irsyadi */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateItemRequest {

  @NotNull private String name;

  @NotNull private Integer quantity;

  @NotNull private BigDecimal price;
}
