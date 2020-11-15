package org.harvanir.pattern.clean.item.provider.gateway.r2dbc;

import org.harvanir.pattern.clean.item.core.entity.CreateItemRequest;
import org.harvanir.pattern.clean.item.core.entity.FindWithDelayRequest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;
import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public class ItemGatewayR2dbc implements ItemGateway {

  private static final String FIND_WITH_DELAY =
      "select i.id, i.name, i.price, i.quantity, i.updated_at, i.created_at %s from items i where id = :$1";

  private static final String FIND_NO_DELAY = String.format(FIND_WITH_DELAY, "");

  private final GatewayBeanMapper mapper;

  private final ItemRepository itemRepository;

  private final DatabaseClient databaseClient;

  public ItemGatewayR2dbc(
      GatewayBeanMapper gatewayBeanMapper,
      ItemRepository itemRepository,
      DatabaseClient databaseClient) {
    this.mapper = gatewayBeanMapper;
    this.itemRepository = itemRepository;
    this.databaseClient = databaseClient;
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
  public Mono<ItemResponse> findById(Long id) {
    return itemRepository.findById(id).map(mapper::map);
  }

  @Override
  public Mono<ItemResponse> findWithDelay(FindWithDelayRequest request) {
    String sql = getSql(request.getDelaySeconds());

    return databaseClient
        .execute(sql)
        .bind(0, request.getId())
        .as(ItemResponse.class)
        .fetch()
        .one();
  }

  private String getSql(Float delaySeconds) {
    if (delaySeconds != null && delaySeconds > 0) {
      return String.format(FIND_WITH_DELAY, String.format(", pg_sleep(%s)", delaySeconds));
    }

    return FIND_NO_DELAY;
  }
}
