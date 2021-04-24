package org.harvanir.pattern.clean.item.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/** @author Harvan Irsyadi */
@Getter
@Setter
@ToString
public class CreateItemRequest {

  @NotNull private String name;

  @NotNull private Integer quantity;

  @NotNull private BigDecimal price;
}
