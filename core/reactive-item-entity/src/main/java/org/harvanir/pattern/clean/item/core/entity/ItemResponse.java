package org.harvanir.pattern.clean.item.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
