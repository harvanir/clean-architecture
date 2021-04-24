package org.harvanir.pattern.clean.item.provider.gateway.r2dbc.repository;

import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.model.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ItemRepository extends ReactiveCrudRepository<Item, Long> {

    Mono<Item> findByName(String name);


}
