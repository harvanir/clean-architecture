package org.harvanir.pattern.clean.item.provider.gateway.r2dbc.repository;

import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.model.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends ReactiveCrudRepository<Item, Long> {}
