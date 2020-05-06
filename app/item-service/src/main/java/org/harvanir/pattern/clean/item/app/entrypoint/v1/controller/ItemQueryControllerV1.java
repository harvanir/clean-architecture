package org.harvanir.pattern.clean.item.app.entrypoint.v1.controller;

import org.harvanir.pattern.clean.item.app.entrypoint.v1.entity.ItemResponseRest;
import org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter.BeanMapper;
import org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter.ItemFindPaginationPresenterImpl;
import org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter.ItemFindPresenterImpl;
import org.harvanir.pattern.clean.item.core.usecase.find.ItemFindUseCase;
import org.harvanir.pattern.clean.item.core.usecase.findpaginate.ItemFindPaginationUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author Harvan Irsyadi */
@RequestMapping(ApiPathV1.V1_ITEMS)
@RestController
public class ItemQueryControllerV1 {

  private final ItemFindUseCase itemFindUseCase;

  private final ItemFindPaginationUseCase itemFindPaginationUseCase;

  private final BeanMapper beanMapper;

  public ItemQueryControllerV1(
      ItemFindUseCase itemFindUseCase,
      ItemFindPaginationUseCase itemFindPaginationUseCase,
      BeanMapper beanMapper) {
    this.itemFindUseCase = itemFindUseCase;
    this.itemFindPaginationUseCase = itemFindPaginationUseCase;
    this.beanMapper = beanMapper;
  }

  @GetMapping
  public Page<ItemResponseRest> findAll(Pageable pageable) {
    ItemFindPaginationPresenterImpl presenter = new ItemFindPaginationPresenterImpl(beanMapper);
    itemFindPaginationUseCase.findPagination(pageable, presenter);

    return presenter.getResponses();
  }

  @GetMapping("/{id}")
  public ItemResponseRest find(@PathVariable Long id) {
    ItemFindPresenterImpl presenter = new ItemFindPresenterImpl(beanMapper);
    itemFindUseCase.find(id, presenter);

    return presenter.getResponse();
  }
}
