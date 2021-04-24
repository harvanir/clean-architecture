package org.harvanir.pattern.clean.item.core.usecase.find;

import org.harvanir.pattern.clean.item.core.entity.FindWithDelayRequest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;

/** @author Harvan Irsyadi */
public class DefaultItemFindWithDelayUseCase implements ItemFindWithDelayUseCase {

  private final ItemGateway itemGateway;

  public DefaultItemFindWithDelayUseCase(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  @Override
  public void execute(FindWithDelayRequest request, ItemFindPresenter presenter) {
    ItemResponse response = itemGateway.findWithDelay(request);

    presenter.present(response);
  }
}
