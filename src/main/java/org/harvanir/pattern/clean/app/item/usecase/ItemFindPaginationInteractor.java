package org.harvanir.pattern.clean.app.item.usecase;

import org.springframework.data.domain.Pageable;

/** @author Harvan Irsyadi */
public interface ItemFindPaginationInteractor {

  void findPagination(Pageable pageable, ItemFindPaginationPresenter presenter);
}
