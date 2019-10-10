package org.harvanir.pattern.clean.app.item.usecase;

import org.harvanir.pattern.clean.app.item.entity.ItemIncreaseRequest;
import org.harvanir.pattern.clean.app.item.entity.ItemResponse;
import org.harvanir.pattern.clean.app.item.gateway.ItemGateway;

/** @author Harvan Irsyadi */
public class ItemIncreaseInteractorImpl implements ItemIncreaseInteractor {

  private final ItemGateway itemGateway;

  public ItemIncreaseInteractorImpl(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  @Override
  public void increase(ItemIncreaseRequest request, ItemIncreasePresenter presenter) {
    ItemResponse response = itemGateway.increase(request.getId(), request.getIncrement());

    presenter.present(response);
  }
}
