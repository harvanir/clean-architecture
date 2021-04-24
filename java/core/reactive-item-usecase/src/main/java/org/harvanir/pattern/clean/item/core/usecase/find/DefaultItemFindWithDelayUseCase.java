package org.harvanir.pattern.clean.item.core.usecase.find;

import org.harvanir.pattern.clean.item.core.entity.FindWithDelayRequest;
import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public class DefaultItemFindWithDelayUseCase implements ItemFindWithDelayUseCase {

  private final ItemGateway itemGateway;

  public DefaultItemFindWithDelayUseCase(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  @Override
  public Mono<Void> execute(FindWithDelayRequest request, ItemFindPresenter presenter) {
    return itemGateway.findWithDelay(request).flatMap(presenter::execute);
  }
}
