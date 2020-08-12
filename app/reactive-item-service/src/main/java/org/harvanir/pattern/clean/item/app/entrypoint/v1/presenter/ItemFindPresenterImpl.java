package org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter;

import org.harvanir.pattern.clean.item.app.entrypoint.v1.entity.ItemResponseRest;
import org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter.common.PresenterUtils;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.usecase.find.ItemFindPresenter;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public class ItemFindPresenterImpl implements ItemFindPresenter {

  private final BeanMapper beanMapper;

  private ItemResponseRest response;

  public ItemFindPresenterImpl(BeanMapper beanMapper) {
    this.beanMapper = beanMapper;
  }

  @Override
  public Mono<Void> execute(ItemResponse itemResponse) {
    return PresenterUtils.present(() -> response = beanMapper.mapToItemResponse(itemResponse));
  }

  public Mono<ItemResponseRest> getResponse() {
    return PresenterUtils.getResponse(() -> response);
  }
}
