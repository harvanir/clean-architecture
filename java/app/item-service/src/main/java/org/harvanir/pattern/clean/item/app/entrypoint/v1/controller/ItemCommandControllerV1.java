package org.harvanir.pattern.clean.item.app.entrypoint.v1.controller;

import org.harvanir.pattern.clean.item.app.entrypoint.v1.entity.ItemResponseRest;
import org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter.BeanMapper;
import org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter.ItemCreationPresenterImpl;
import org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter.ItemIncreasePresenterImpl;
import org.harvanir.pattern.clean.item.core.entity.CreateItemRequest;
import org.harvanir.pattern.clean.item.core.entity.ItemIncreaseRequest;
import org.harvanir.pattern.clean.item.core.usecase.create.ItemCreationUseCase;
import org.harvanir.pattern.clean.item.core.usecase.increase.ItemIncreaseUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Harvan Irsyadi */
@RequestMapping(ApiPathV1.V1_ITEMS)
@RestController
public class ItemCommandControllerV1 {

  private final ItemCreationUseCase itemCreationUseCase;

  private final ItemIncreaseUseCase itemIncreaseUseCase;

  private final BeanMapper beanMapper;

  public ItemCommandControllerV1(
      ItemCreationUseCase itemCreationUseCase,
      ItemIncreaseUseCase itemIncreaseUseCase,
      BeanMapper beanMapper) {
    this.itemCreationUseCase = itemCreationUseCase;
    this.itemIncreaseUseCase = itemIncreaseUseCase;
    this.beanMapper = beanMapper;
  }

  @PostMapping
  public ItemResponseRest create(@RequestBody CreateItemRequest createItemRequest) {
    ItemCreationPresenterImpl presenter = new ItemCreationPresenterImpl(beanMapper);
    itemCreationUseCase.execute(createItemRequest, presenter);

    return presenter.getResponse();
  }

  @PutMapping("/{id}/increase")
  public ItemResponseRest increase(@PathVariable Long id) {
    ItemIncreasePresenterImpl presenter = new ItemIncreasePresenterImpl(beanMapper);
    itemIncreaseUseCase.increase(
        ItemIncreaseRequest.builder().id(id).increment(1).build(), presenter);

    return presenter.getResponse();
  }
}
