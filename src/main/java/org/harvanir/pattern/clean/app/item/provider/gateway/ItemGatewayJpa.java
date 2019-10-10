package org.harvanir.pattern.clean.app.item.provider.gateway;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import org.harvanir.pattern.clean.app.item.driver.jpa.model.Item;
import org.harvanir.pattern.clean.app.item.driver.jpa.model.QItem;
import org.harvanir.pattern.clean.app.item.driver.jpa.repository.ItemJpaRepository;
import org.harvanir.pattern.clean.app.item.entity.CreateItemRequest;
import org.harvanir.pattern.clean.app.item.entity.ItemResponse;
import org.harvanir.pattern.clean.app.item.gateway.ItemGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Harvan Irsyadi */
public class ItemGatewayJpa implements ItemGateway {

  private GatewayBeanMapper mapper;

  private ItemJpaRepository itemJpaRepository;

  public ItemGatewayJpa(GatewayBeanMapper gatewayBeanMapper, ItemJpaRepository itemJpaRepository) {
    this.mapper = gatewayBeanMapper;
    this.itemJpaRepository = itemJpaRepository;
  }

  @Override
  public ItemResponse save(CreateItemRequest request) {
    return mapper.map(itemJpaRepository.save(mapper.map(request)));
  }

  @Override
  public ItemResponse increase(Long id, int increment) {
    Item item = itemJpaRepository.getOne(id);
    item.setQuantity(item.getQuantity() + increment);

    return mapper.map(itemJpaRepository.save(item));
  }

  private QItem getItem() {
    return QItem.item;
  }

  private QBean<ItemResponse> getExpressionBase() {
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
    return itemJpaRepository.findAll(getExpressionBase(), pageable);
  }

  @Override
  public ItemResponse findById(Long id) {
    return itemJpaRepository.findOne(getExpressionBase(), getItem().id.eq(id));
  }
}
