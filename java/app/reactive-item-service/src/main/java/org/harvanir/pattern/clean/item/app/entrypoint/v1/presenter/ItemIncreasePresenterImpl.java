package org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter;

import org.harvanir.pattern.clean.item.app.entrypoint.v1.entity.ItemResponseRest;
import org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter.common.PresenterUtils;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.usecase.increase.ItemIncreasePresenter;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public class ItemIncreasePresenterImpl implements ItemIncreasePresenter {

  private final BeanMapper beanMapper;

  private ItemResponseRest response;

  public ItemIncreasePresenterImpl(BeanMapper beanMapper) {
    this.beanMapper = beanMapper;
  }

  @Override
  public Mono<Void> present(ItemResponse itemResponse) {
    return PresenterUtils.present(() -> response = beanMapper.mapToItemResponse(itemResponse));
  }

  public Mono<ItemResponseRest> getResponse() {
    return PresenterUtils.getResponse(() -> response);
  }
}
