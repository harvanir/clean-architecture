package org.harvanir.pattern.clean.item.core.usecase.findpaginate;

import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** @author Harvan Irsyadi */
public class DefaultItemFindPaginationUseCase implements ItemFindPaginationUseCase {

  private final ItemGateway itemGateway;

  public DefaultItemFindPaginationUseCase(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  @Override
  public void findPagination(Pageable pageable, ItemFindPaginationPresenter presenter) {
    Page<ItemResponse> responses = itemGateway.findAll(pageable);

    presenter.present(responses);
  }
}
