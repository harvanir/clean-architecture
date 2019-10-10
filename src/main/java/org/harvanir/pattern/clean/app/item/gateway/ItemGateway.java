package org.harvanir.pattern.clean.app.item.gateway;

import org.harvanir.pattern.clean.app.item.entity.CreateItemRequest;
import org.harvanir.pattern.clean.app.item.entity.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Harvan Irsyadi */
public interface ItemGateway {

  ItemResponse save(CreateItemRequest request);

  Page<ItemResponse> findAll(Pageable pageable);

  ItemResponse findById(Long id);

  ItemResponse increase(Long id, int increment);
}
