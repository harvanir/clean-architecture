package org.harvanir.pattern.clean.app.item.usecase;

import org.harvanir.pattern.clean.app.item.entity.CreateItemRequest;
import org.harvanir.pattern.clean.app.item.entity.ItemResponse;
import org.harvanir.pattern.clean.app.item.gateway.ItemGateway;

/** @author Harvan Irsyadi */
public class ItemCreationInteractorImpl implements ItemCreationInteractor {

  private final ItemGateway itemGateway;

  public ItemCreationInteractorImpl(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  @Override
  public void execute(CreateItemRequest request, ItemCreationPresenter presenter) {
    ItemResponse response = itemGateway.save(request);

    presenter.execute(response);
  }
}
