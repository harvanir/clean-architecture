package org.harvanir.pattern.clean.app.item.adapter.v1.presenter;

import org.harvanir.pattern.clean.app.item.entity.ItemResponse;
import org.harvanir.pattern.clean.app.item.usecase.ItemFindPresenter;

/** @author Harvan Irsyadi */
public class ItemFindPresenterImpl implements ItemFindPresenter {

  private ItemResponse response;

  @Override
  public void present(ItemResponse response) {
    this.response = response;
  }

  public ItemResponse getResponse() {
    return response;
  }
}
