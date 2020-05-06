package org.harvanir.pattern.clean.item.provider.gateway.jpa;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import org.harvanir.pattern.clean.item.core.entity.CreateItemRequest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;
import org.harvanir.pattern.clean.item.provider.gateway.jpa.model.Item;
import org.harvanir.pattern.clean.item.provider.gateway.jpa.model.QItem;
import org.harvanir.pattern.clean.item.provider.gateway.jpa.repository.ItemJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/** @author Harvan Irsyadi */
public class ItemGatewayJpa implements ItemGateway {

  private final GatewayBeanMapper mapper;

  private final ItemJpaRepository itemJpaRepository;

  public ItemGatewayJpa(GatewayBeanMapper gatewayBeanMapper, ItemJpaRepository itemJpaRepository) {
    this.mapper = gatewayBeanMapper;
    this.itemJpaRepository = itemJpaRepository;
  }

  @Override
  public ItemResponse save(CreateItemRequest request) {
    return mapper.map(itemJpaRepository.save(mapper.map(request)));
  }

  @Transactional
  @Override
  public ItemResponse increase(Long id, int increment) {
    Item item = itemJpaRepository.getOne(id);
    item.setQuantity(item.getQuantity() + increment);

    return mapper.map(itemJpaRepository.save(item));
  }

  private QItem getItem() {
    return QItem.item;
  }

  private QBean<ItemResponse> getBaseExpression() {
    QItem item = getItem();

    return Projections.bean(
        ItemResponse.class,
        item.id,
        item.name,
        item.price,
        item.quantity,
        item.createdAt,
        item.updatedAt);
  }

  @Override
  public Page<ItemResponse> findAll(Pageable pageable) {
    return itemJpaRepository.findAll(getBaseExpression(), pageable);
  }

  @Override
  public ItemResponse findById(Long id) {
    return itemJpaRepository.findOne(getBaseExpression(), getItem().id.eq(id));
  }
}
