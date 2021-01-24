package org.harvanir.pattern.clean.item.provider.gateway.r2dbc;

import io.r2dbc.spi.Row;
import org.harvanir.pattern.clean.item.core.entity.CreateItemRequest;
import org.harvanir.pattern.clean.item.core.entity.FindWithDelayRequest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;
import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;

/** @author Harvan Irsyadi */
public class ItemGatewayR2dbc implements ItemGateway {

  private static final String FIND_WITH_DELAY =
      "select i.id, i.name, i.price, i.quantity, i.updated_at, i.created_at %s from items i where id = :$1";

  private static final Function<Row, ItemResponse> itemMapper =
      row ->
          ItemResponse.builder()
              .id(row.get(0, Long.class))
              .name(row.get(1, String.class))
              .price(row.get(2, BigDecimal.class))
              .quantity(row.get(3, Integer.class))
              .updatedAt(row.get(4, LocalDateTime.class))
              .createdAt(row.get(5, LocalDateTime.class))
              .build();

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

  @Transactional
  @Override
  public Mono<ItemResponse> save(CreateItemRequest request) {
    return itemRepository.save(mapper.map(request)).map(mapper::map);
  }

  @Transactional
  @Override
  public Mono<ItemResponse> increase(Long id, int increment) {
    return itemRepository
        .findById(id)
        .doOnNext(item -> item.setQuantity(item.getQuantity() + increment))
        .flatMap(itemRepository::save)
        .map(mapper::map);
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

    return databaseClient.sql(sql).bind(0, request.getId()).map(itemMapper).one();
  }

  private String getSql(Float delaySeconds) {
    if (delaySeconds != null && delaySeconds > 0) {
      return String.format(FIND_WITH_DELAY, String.format(", pg_sleep(%s)", delaySeconds));
    }

    return FIND_NO_DELAY;
  }
}
