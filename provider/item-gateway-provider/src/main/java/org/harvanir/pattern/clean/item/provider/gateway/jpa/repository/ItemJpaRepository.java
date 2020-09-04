package org.harvanir.pattern.clean.item.provider.gateway.jpa.repository;

import lombok.SneakyThrows;
import org.harvanir.pattern.clean.item.provider.gateway.jpa.model.Item;
import org.harvanir.pattern.clean.jpa.support.QueryDslPredicateAndProjectionExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ItemJpaRepository
    extends JpaRepository<Item, Long>, QueryDslPredicateAndProjectionExecutor<Item> {

  @SneakyThrows
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  default void lock(Long id) {
    Item item = getOne(id);

    Thread.sleep(1000);

    if (item.getIsFirst() != null && item.getIsFirst()) {
      item.setIsFirst(false);

      save(item);
    } else {
      throw new RuntimeException("Is not the first request.");
    }
  }
}
