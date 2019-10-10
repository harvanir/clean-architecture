package org.harvanir.pattern.clean.app.item.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/** @author Harvan Irsyadi */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateItemRequest {

  @NotNull private String name;

  @NotNull private Integer quantity;

  @NotNull private BigDecimal price;
}
