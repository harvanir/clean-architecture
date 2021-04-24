package org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter;

import org.harvanir.pattern.clean.item.app.entrypoint.v1.entity.ItemResponseRest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.usecase.create.ItemCreationPresenter;

/** @author Harvan Irsyadi */
public class ItemCreationPresenterImpl implements ItemCreationPresenter {

  private final BeanMapper beanMapper;

  private ItemResponseRest response;

  public ItemCreationPresenterImpl(BeanMapper beanMapper) {
    this.beanMapper = beanMapper;
  }

  @Override
  public void execute(ItemResponse response) {
    this.response = beanMapper.mapToItemResponse(response);
  }

  public ItemResponseRest getResponse() {
    return response;
  }
}
