package org.harvanir.pattern.clean.item.core.usecase.find;

import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public class DefaultItemFindUseCase implements ItemFindUseCase {

  private final ItemGateway itemGateway;

  public DefaultItemFindUseCase(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  @Override
  public Mono<Void> execute(Long id, ItemFindPresenter presenter) {
    return itemGateway.findById(id).flatMap(presenter::execute);
  }
}
