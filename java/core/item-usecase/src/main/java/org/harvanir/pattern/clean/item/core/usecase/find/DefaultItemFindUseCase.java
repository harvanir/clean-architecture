package org.harvanir.pattern.clean.item.core.usecase.find;

import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/** @author Harvan Irsyadi */
public class DefaultItemFindUseCase implements ItemFindUseCase {

  private final ItemGateway itemGateway;

  public DefaultItemFindUseCase(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
  @Override
  public void execute(Long id, ItemFindPresenter presenter) {
    ItemResponse response = itemGateway.findById(id);

    presenter.present(response);
  }
}
