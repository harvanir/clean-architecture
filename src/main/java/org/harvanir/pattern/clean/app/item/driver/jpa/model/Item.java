package org.harvanir.pattern.clean.app.item.driver.jpa.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.util.Date;
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

  private static final String SEQ_GENERATORS = "item_generators";

  private static final String SEQ_NAME = "seq_items";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_GENERATORS)
  @SequenceGenerator(name = SEQ_GENERATORS, sequenceName = SEQ_NAME)
  @Column
  private Long id;

  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<OrderItem> orderItems = new HashSet<>();

  @Column private String name;

  @Column private Integer quantity;

  @Column private BigDecimal price;

  @CreatedDate @Column private Date createdAt;

  @LastModifiedBy @Column private Date updatedAt;

  @Version @Column private long version;
}
