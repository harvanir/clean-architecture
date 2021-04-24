package org.harvanir.pattern.clean.item.app.entrypoint.v1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/** @author Harvan Irsyadi */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemResponseRest {

  private Long id;

  private String name;

  private Integer quantity;

  private BigDecimal price;

  private Date createdAt;

  private Date updatedAt;
}
