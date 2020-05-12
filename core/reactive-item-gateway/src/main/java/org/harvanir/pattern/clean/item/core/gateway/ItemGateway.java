package org.harvanir.pattern.clean.item.core.gateway;

import org.harvanir.pattern.clean.item.core.entity.CreateItemRequest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public interface ItemGateway {

  Mono<ItemResponse> save(CreateItemRequest request);

  Page<ItemResponse> findAll(Pageable pageable);

  ItemResponse findById(Long id);

  ItemResponse increase(Long id, int increment);
}
