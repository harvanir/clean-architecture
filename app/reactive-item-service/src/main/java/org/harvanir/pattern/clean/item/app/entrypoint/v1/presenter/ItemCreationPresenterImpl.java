package org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter;

import org.harvanir.pattern.clean.item.app.entrypoint.v1.entity.ItemResponseRest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.usecase.create.ItemCreationPresenter;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public class ItemCreationPresenterImpl implements ItemCreationPresenter {

  private final BeanMapper beanMapper;

  private ItemResponseRest response;

  public ItemCreationPresenterImpl(BeanMapper beanMapper) {
    this.beanMapper = beanMapper;
  }

  @Override
  public Mono<Void> execute(ItemResponse response) {
    this.response = beanMapper.mapToItemResponse(response);
    return Mono.just(true).then();
  }

  public Mono<ItemResponseRest> getResponse() {
    return Mono.fromSupplier(() -> response);
  }
}
