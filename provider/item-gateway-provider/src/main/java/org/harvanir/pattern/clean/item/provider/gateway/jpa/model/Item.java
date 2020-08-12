package org.harvanir.pattern.clean.item.provider.gateway.jpa.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/** @author Harvan Irsyadi */
@Getter
@Setter
@DynamicUpdate
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = TableConstant.ITEMS)
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<OrderItem> orderItems = new HashSet<>();

  @Column private String name;

  @Column private Integer quantity;

  @Column private BigDecimal price;

  @CreatedDate @Column private LocalDateTime createdAt;

  @LastModifiedDate @Column private LocalDateTime updatedAt;

  @Version @Column private long version;
}
