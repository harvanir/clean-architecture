//package org.harvanir.pattern.clean.item.provider.gateway.jpa.model;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EntityListeners;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//import javax.persistence.Version;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//@Getter
//@Setter
//@Entity
//@EntityListeners(AuditingEntityListener.class)
//@Table(name = TableConstant.ORDERS)
//public class Order {
//
//  private static final String SEQ_GENERATORS = "order_generators";
//
//  private static final String SEQ_NAME = "seq_orders";
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_GENERATORS)
//  @SequenceGenerator(name = SEQ_GENERATORS, sequenceName = SEQ_NAME)
//  @Column
//  private Long id;
//
//  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//  private Set<OrderItem> orderItems = new HashSet<>();
//
//  @Column private String status;
//
//  @Column private Short statusCode;
//
//  @CreatedDate @Column private Date createdAt;
//
//  @LastModifiedDate @Column private Date updatedAt;
//
//  @Version @Column private int version;
//}
