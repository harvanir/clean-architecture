package org.harvanir.pattern.clean.item.app.entrypoint.v1.controller;

import org.harvanir.pattern.clean.item.app.entrypoint.v1.entity.ItemResponseRest;
import org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter.BeanMapper;
import org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter.ItemFindPresenterImpl;
import org.harvanir.pattern.clean.item.core.entity.FindWithDelayRequest;
import org.harvanir.pattern.clean.item.core.usecase.find.ItemFindUseCase;
import org.harvanir.pattern.clean.item.core.usecase.find.ItemFindWithDelayUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
@RequestMapping(ApiPathV1.V1_ITEMS)
@RestController
public class ItemQueryControllerV1 {

  private final ItemFindUseCase itemFindUseCase;

  private final ItemFindWithDelayUseCase itemFindWithDelayUseCase;

  private final BeanMapper beanMapper;

  public ItemQueryControllerV1(
      ItemFindUseCase itemFindUseCase,
      ItemFindWithDelayUseCase itemFindWithDelayUseCase,
      BeanMapper beanMapper) {
    this.itemFindUseCase = itemFindUseCase;
    this.itemFindWithDelayUseCase = itemFindWithDelayUseCase;
    this.beanMapper = beanMapper;
  }

  @GetMapping("/{id}")
  public Mono<ItemResponseRest> findById(@PathVariable Long id) {
    return Mono.defer(
        () -> {
          ItemFindPresenterImpl presenter = new ItemFindPresenterImpl(beanMapper);

          return itemFindUseCase.execute(id, presenter).then(presenter.getResponse());
        });
  }

  @GetMapping("/{id}/{delaySeconds}")
  public Mono<ItemResponseRest> findByIdAndDelaySeconds(
      @PathVariable Long id, @PathVariable Float delaySeconds) {
    return Mono.defer(
        () -> {
          ItemFindPresenterImpl presenter = new ItemFindPresenterImpl(beanMapper);

          return itemFindWithDelayUseCase
              .execute(
                  FindWithDelayRequest.builder().id(id).delaySeconds(delaySeconds).build(),
                  presenter)
              .then(presenter.getResponse());
        });
  }
}
