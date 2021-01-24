package org.harvanir.pattern.clean.item.core.gateway;

import org.harvanir.pattern.clean.item.core.entity.CreateItemRequest;
import org.harvanir.pattern.clean.item.core.entity.FindWithDelayRequest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public interface ItemGateway {

  Mono<ItemResponse> save(CreateItemRequest request);

  Page<ItemResponse> findAll(Pageable pageable);

  Mono<ItemResponse> findById(Long id);

  Mono<ItemResponse> findWithDelay(FindWithDelayRequest request);

  Mono<ItemResponse> increase(Long id, int increment);
}
