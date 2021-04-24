package org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter;

import org.harvanir.pattern.clean.item.app.entrypoint.v1.entity.ItemResponseRest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.usecase.findpaginate.ItemFindPaginationPresenter;
import org.springframework.data.domain.Page;

/** @author Harvan Irsyadi */
public class ItemFindPaginationPresenterImpl implements ItemFindPaginationPresenter {

  private final BeanMapper beanMapper;

  private Page<ItemResponseRest> responses;

  public ItemFindPaginationPresenterImpl(BeanMapper beanMapper) {
    this.beanMapper = beanMapper;
  }

  @Override
  public void present(Page<ItemResponse> responses) {
    this.responses = responses.map(beanMapper::mapToItemResponse);
  }

  public Page<ItemResponseRest> getResponses() {
    return responses;
  }
}
