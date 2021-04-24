package org.harvanir.pattern.clean.item.core.usecase.create;

import org.harvanir.pattern.clean.item.core.entity.CreateItemRequest;
import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public class DefaultItemCreationUseCase implements ItemCreationUseCase {

  private final ItemGateway itemGateway;

  public DefaultItemCreationUseCase(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  @Override
  public Mono<Void> execute(CreateItemRequest request, ItemCreationPresenter presenter) {
    return itemGateway.save(request).flatMap(presenter::execute);
  }
}
