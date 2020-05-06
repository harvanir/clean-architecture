package org.harvanir.pattern.clean.item.core.usecase.create;

import org.harvanir.pattern.clean.item.core.entity.CreateItemRequest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;

/** @author Harvan Irsyadi */
public class DefaultItemCreationUseCase implements ItemCreationUseCase {

  private final ItemGateway itemGateway;

  public DefaultItemCreationUseCase(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  @Override
  public void execute(CreateItemRequest request, ItemCreationPresenter presenter) {
    ItemResponse response = itemGateway.save(request);

    presenter.execute(response);
  }
}
