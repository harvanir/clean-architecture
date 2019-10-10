package org.harvanir.pattern.clean.app.item.usecase;

import org.harvanir.pattern.clean.app.item.entity.ItemResponse;
import org.harvanir.pattern.clean.app.item.gateway.ItemGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Harvan Irsyadi */
public class ItemFindPaginationInteractorImpl implements ItemFindPaginationInteractor {

  private final ItemGateway itemGateway;

  public ItemFindPaginationInteractorImpl(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  @Override
  public void findPagination(Pageable pageable, ItemFindPaginationPresenter presenter) {
    Page<ItemResponse> responses = itemGateway.findAll(pageable);

    presenter.present(responses);
  }
}
