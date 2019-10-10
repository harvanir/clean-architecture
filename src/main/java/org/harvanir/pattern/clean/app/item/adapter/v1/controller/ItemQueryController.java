package org.harvanir.pattern.clean.app.item.adapter.v1.controller;

import org.harvanir.pattern.clean.app.item.adapter.v1.presenter.ItemFindPaginationPresenterImpl;
import org.harvanir.pattern.clean.app.item.adapter.v1.presenter.ItemFindPresenterImpl;
import org.harvanir.pattern.clean.app.item.entity.ItemResponse;
import org.harvanir.pattern.clean.app.item.usecase.ItemFindInteractor;
import org.harvanir.pattern.clean.app.item.usecase.ItemFindPaginationInteractor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Harvan Irsyadi */
@RequestMapping(ApiPathV1.V1_ITEMS)
@RestController
public class ItemQueryController {

  private final ItemFindInteractor itemFindInteractor;

  private final ItemFindPaginationInteractor itemFindPaginationInteractor;

  public ItemQueryController(
      ItemFindInteractor itemFindInteractor,
      ItemFindPaginationInteractor itemFindPaginationInteractor) {
    this.itemFindInteractor = itemFindInteractor;
    this.itemFindPaginationInteractor = itemFindPaginationInteractor;
  }

  @GetMapping
  public Page<ItemResponse> findAll(Pageable pageable) {
    ItemFindPaginationPresenterImpl presenter = new ItemFindPaginationPresenterImpl();
    itemFindPaginationInteractor.findPagination(pageable, presenter);

    return presenter.getResponses();
  }

  @GetMapping("/{id}")
  public ItemResponse find(@PathVariable Long id) {
    ItemFindPresenterImpl presenter = new ItemFindPresenterImpl();
    itemFindInteractor.find(id, presenter);

    return presenter.getResponse();
  }
}
