package org.harvanir.pattern.clean.item.provider.gateway.jpa;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import org.harvanir.pattern.clean.item.core.entity.CreateItemRequest;
import org.harvanir.pattern.clean.item.core.entity.FindWithDelayRequest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;
import org.harvanir.pattern.clean.item.provider.gateway.jpa.model.Item;
import org.harvanir.pattern.clean.item.provider.gateway.jpa.model.QItem;
import org.harvanir.pattern.clean.item.provider.gateway.jpa.repository.ItemJpaRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.transaction.annotation.Transactional;

/** @author Harvan Irsyadi */
public class ItemGatewayJpa implements ItemGateway {

  private static final String FIND_WITH_DELAY =
      "select i.id, i.name, i.price, i.quantity, i.updated_at, i.created_at %s from items i where id = ?";

  private static final String FIND_NO_DELAY = String.format(FIND_WITH_DELAY, "");

  private final ResultSetExtractor<ItemResponse> resultSetExtractor =
      rs -> {
        if (rs.next()) {
          return ItemResponse.builder()
              .id(rs.getLong(1))
              .name(rs.getString(2))
              .price(rs.getBigDecimal(3))
              .quantity(rs.getInt(4))
              .updatedAt(rs.getTimestamp(5).toLocalDateTime())
              .createdAt(rs.getTimestamp(6).toLocalDateTime())
              .build();
        }

        return null;
      };

  private final BeanPropertyRowMapper<ItemResponse> beanPropertyRowMapper =
      new BeanPropertyRowMapper<>(ItemResponse.class);

  private final GatewayBeanMapper mapper;

  private final ItemJpaRepository itemJpaRepository;

  private final JdbcTemplate jdbcTemplate;

  public ItemGatewayJpa(
      GatewayBeanMapper gatewayBeanMapper,
      ItemJpaRepository itemJpaRepository,
      JdbcTemplate jdbcTemplate) {
    this.mapper = gatewayBeanMapper;
    this.itemJpaRepository = itemJpaRepository;
    this.jdbcTemplate = jdbcTemplate;
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
    //    return mapper.map(itemJpaRepository.findById(id).orElse(null));
    //    return itemJpaRepository.findOne(getBaseExpression(), getItem().id.eq(id));
    return itemJpaRepository.findUsingId(id);
  }

  private String getSql(Float delaySeconds) {
    if (delaySeconds != null && delaySeconds > 0) {
      return String.format(FIND_WITH_DELAY, String.format(", pg_sleep(%s)", delaySeconds));
    }

    return FIND_NO_DELAY;
  }

  @Override
  public ItemResponse findWithDelay(FindWithDelayRequest request) {
    String sql = getSql(request.getDelaySeconds());

    return queryForObject(sql, request);
    //    return queryResultSetExtractor(sql, request);
    //    return queryRowMapper(sql, request);
  }

  private ItemResponse queryForObject(String sql, FindWithDelayRequest request) {
    Object[] args = new Object[] {request.getId()};
    return jdbcTemplate.queryForObject(sql, args, beanPropertyRowMapper);
  }

  private PreparedStatementSetter getPreparedStatementSetter(FindWithDelayRequest request) {
    return ps -> ps.setLong(1, request.getId());
  }

  private ItemResponse queryResultSetExtractor(String sql, FindWithDelayRequest request) {
    return jdbcTemplate.query(sql, getPreparedStatementSetter(request), resultSetExtractor);
  }

  private ItemResponse queryRowMapper(String sql, FindWithDelayRequest request) {
    return DataAccessUtils.uniqueResult(
        jdbcTemplate.query(sql, getPreparedStatementSetter(request), beanPropertyRowMapper));
  }
}
