package org.harvanir.pattern.clean.app.item.adapter.v1.controller;

import org.harvanir.pattern.clean.app.item.adapter.v1.presenter.ItemCreationPresenterImpl;
import org.harvanir.pattern.clean.app.item.adapter.v1.presenter.ItemIncreasePresenterImpl;
import org.harvanir.pattern.clean.app.item.entity.CreateItemRequest;
import org.harvanir.pattern.clean.app.item.entity.ItemIncreaseRequest;
import org.harvanir.pattern.clean.app.item.entity.ItemResponse;
import org.harvanir.pattern.clean.app.item.usecase.ItemCreationInteractor;
import org.harvanir.pattern.clean.app.item.usecase.ItemIncreaseInteractor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Harvan Irsyadi */
@RequestMapping(ApiPathV1.V1_ITEMS)
@RestController
public class ItemCommandController {

  private final ItemCreationInteractor itemCreationInteractor;

  private final ItemIncreaseInteractor itemIncreaseInteractor;

  public ItemCommandController(
      ItemCreationInteractor itemCreationInteractor,
      ItemIncreaseInteractor itemIncreaseInteractor) {
    this.itemCreationInteractor = itemCreationInteractor;
    this.itemIncreaseInteractor = itemIncreaseInteractor;
  }

  @PostMapping
  public ItemResponse create(@RequestBody CreateItemRequest createItemRequest) {
    ItemCreationPresenterImpl presenter = new ItemCreationPresenterImpl();
    itemCreationInteractor.execute(createItemRequest, presenter);

    return presenter.getResponse();
  }

  @PutMapping("/{id}/increase")
  public ItemResponse increase(@PathVariable Long id) {
    ItemIncreasePresenterImpl presenter = new ItemIncreasePresenterImpl();
    itemIncreaseInteractor.increase(
        ItemIncreaseRequest.builder().id(id).increment(1).build(), presenter);

    return presenter.getResponse();
  }
}
