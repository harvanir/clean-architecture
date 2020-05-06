package org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter;

import org.harvanir.pattern.clean.item.app.entrypoint.v1.entity.ItemResponseRest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.usecase.increase.ItemIncreasePresenter;

/** @author Harvan Irsyadi */
public class ItemIncreasePresenterImpl implements ItemIncreasePresenter {

  private final BeanMapper beanMapper;

  private ItemResponseRest response;

  public ItemIncreasePresenterImpl(BeanMapper beanMapper) {
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
