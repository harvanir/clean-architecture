package org.harvanir.pattern.clean.app.item.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemResponse {

  private Long id;

  private String name;

  private Integer quantity;

  private BigDecimal price;

  private Date createdAt;

  private Date updatedAt;
}
