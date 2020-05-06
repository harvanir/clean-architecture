package org.harvanir.pattern.clean.item.core.usecase.find;

import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;

/** @author Harvan Irsyadi */
public class DefaultItemFindUseCase implements ItemFindUseCase {

  private final ItemGateway itemGateway;

  public DefaultItemFindUseCase(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  @Override
  public void find(Long id, ItemFindPresenter presenter) {
    ItemResponse response = itemGateway.findById(id);

    presenter.present(response);
  }
}
