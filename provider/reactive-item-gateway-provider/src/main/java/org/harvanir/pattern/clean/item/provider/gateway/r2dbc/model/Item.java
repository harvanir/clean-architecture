package org.harvanir.pattern.clean.item.provider.gateway.r2dbc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** @author Harvan Irsyadi */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(TableConstant.ITEMS)
public class Item {

  @Id private Long id;

  private String name;

  private Integer quantity;

  private BigDecimal price;

  @CreatedDate private LocalDateTime createdAt;

  @LastModifiedDate private LocalDateTime updatedAt;

  private @Version long version;
}
