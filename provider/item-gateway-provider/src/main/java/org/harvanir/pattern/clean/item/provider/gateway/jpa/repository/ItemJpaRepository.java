package org.harvanir.pattern.clean.item.provider.gateway.jpa.repository;

import org.harvanir.pattern.clean.item.provider.gateway.jpa.model.Item;
import org.harvanir.pattern.clean.jpa.support.QueryDslPredicateAndProjectionExecutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository
    extends JpaRepository<Item, Long>, QueryDslPredicateAndProjectionExecutor<Item> {}
