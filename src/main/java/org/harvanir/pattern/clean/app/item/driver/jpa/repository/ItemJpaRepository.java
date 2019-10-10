package org.harvanir.pattern.clean.app.item.driver.jpa.repository;

import org.harvanir.pattern.clean.app.base.driver.jpa.support.QueryDslPredicateAndProjectionExecutor;
import org.harvanir.pattern.clean.app.item.driver.jpa.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository
    extends JpaRepository<Item, Long>, QueryDslPredicateAndProjectionExecutor<Item> {}
