package org.harvanir.pattern.clean.item.provider.gateway.r2dbc;

import org.harvanir.pattern.clean.item.core.entity.CreateItemRequest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;
import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public class ItemGatewayR2dbc implements ItemGateway {

  private final GatewayBeanMapper mapper;

  private final ItemRepository itemRepository;

  public ItemGatewayR2dbc(GatewayBeanMapper gatewayBeanMapper, ItemRepository itemRepository) {
    this.mapper = gatewayBeanMapper;
    this.itemRepository = itemRepository;
  }

  @Override
  public Mono<ItemResponse> save(CreateItemRequest request) {
    return itemRepository.save(mapper.map(request)).map(mapper::map);
  }

  @Transactional
  @Override
  public ItemResponse increase(Long id, int increment) {
    return null;
  }

  @Override
  public Page<ItemResponse> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public ItemResponse findById(Long id) {
    return null;
  }
}
