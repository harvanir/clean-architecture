package org.harvanir.pattern.clean.app.item.adapter.v1.presenter;

import org.harvanir.pattern.clean.app.item.entity.ItemResponse;
import org.harvanir.pattern.clean.app.item.usecase.ItemFindPaginationPresenter;
import org.springframework.data.domain.Page;

/** @author Harvan Irsyadi */
public class ItemFindPaginationPresenterImpl implements ItemFindPaginationPresenter {

  private Page<ItemResponse> responses;

  @Override
  public void present(Page<ItemResponse> responses) {
    this.responses = responses;
  }

  public Page<ItemResponse> getResponses() {
    return responses;
  }
}
