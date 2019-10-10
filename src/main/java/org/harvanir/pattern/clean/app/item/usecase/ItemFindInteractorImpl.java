package org.harvanir.pattern.clean.app.item.usecase;

import org.harvanir.pattern.clean.app.item.entity.ItemResponse;
import org.harvanir.pattern.clean.app.item.gateway.ItemGateway;

/** @author Harvan Irsyadi */
public class ItemFindInteractorImpl implements ItemFindInteractor {

  private final ItemGateway itemGateway;

  public ItemFindInteractorImpl(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  @Override
  public void find(Long id, ItemFindPresenter presenter) {
    ItemResponse response = itemGateway.findById(id);

    presenter.present(response);
  }
}
