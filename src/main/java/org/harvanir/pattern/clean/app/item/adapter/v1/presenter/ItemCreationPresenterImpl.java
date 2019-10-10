package org.harvanir.pattern.clean.app.item.adapter.v1.presenter;

import org.harvanir.pattern.clean.app.item.entity.ItemResponse;
import org.harvanir.pattern.clean.app.item.usecase.ItemCreationPresenter;

/** @author Harvan Irsyadi */
public class ItemCreationPresenterImpl implements ItemCreationPresenter {

  private ItemResponse response;

  @Override
  public void execute(ItemResponse response) {
    this.response = response;
  }

  public ItemResponse getResponse() {
    return response;
  }
}
