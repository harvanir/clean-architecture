package org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter;

import org.harvanir.pattern.clean.item.app.entrypoint.v1.entity.ItemResponseRest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.usecase.find.ItemFindPresenter;

/** @author Harvan Irsyadi */
public class ItemFindPresenterImpl implements ItemFindPresenter {

  private final BeanMapper beanMapper;

  private ItemResponseRest response;

  public ItemFindPresenterImpl(BeanMapper beanMapper) {
    this.beanMapper = beanMapper;
  }

  @Override
  public void present(ItemResponse response) {
    this.response = beanMapper.mapToItemResponse(response);
  }

  public ItemResponseRest getResponse() {
    return response;
  }
}
