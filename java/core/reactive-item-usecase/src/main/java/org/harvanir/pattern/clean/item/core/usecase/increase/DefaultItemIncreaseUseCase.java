package org.harvanir.pattern.clean.item.core.usecase.increase;

import org.harvanir.pattern.clean.item.core.entity.ItemIncreaseRequest;
import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public class DefaultItemIncreaseUseCase implements ItemIncreaseUseCase {

  private final ItemGateway itemGateway;

  public DefaultItemIncreaseUseCase(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  @Override
  public Mono<Void> execute(ItemIncreaseRequest request, ItemIncreasePresenter presenter) {
    return itemGateway
        .increase(request.getId(), request.getIncrement())
        .flatMap(presenter::present);
  }
}
