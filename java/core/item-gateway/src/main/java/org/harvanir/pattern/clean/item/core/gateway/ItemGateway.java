package org.harvanir.pattern.clean.item.core.gateway;

import org.harvanir.pattern.clean.item.core.entity.CreateItemRequest;
import org.harvanir.pattern.clean.item.core.entity.FindWithDelayRequest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Harvan Irsyadi */
public interface ItemGateway {

  ItemResponse save(CreateItemRequest request);

  Page<ItemResponse> findAll(Pageable pageable);

  ItemResponse findById(Long id);

  ItemResponse findWithDelay(FindWithDelayRequest request);

  ItemResponse increase(Long id, int increment);
}
