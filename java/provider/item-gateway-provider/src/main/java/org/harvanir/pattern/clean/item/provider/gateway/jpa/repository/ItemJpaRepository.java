package org.harvanir.pattern.clean.item.provider.gateway.jpa.repository;

import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.provider.gateway.jpa.model.Item;
import org.harvanir.pattern.clean.jpa.support.QueryDslPredicateAndProjectionExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemJpaRepository
    extends JpaRepository<Item, Long>, QueryDslPredicateAndProjectionExecutor<Item> {

  @Query(
      "select "
          + "new org.harvanir.pattern.clean.item.core.entity.ItemResponse(id, name, quantity, price, createdAt, updatedAt) "
          + "from Item where id = :id")
  //    @QueryHints(@QueryHint(name = HINT_CACHEABLE, value = "true")) // uncomment to use 2nd cache
  ItemResponse findUsingId(Long id);
}
