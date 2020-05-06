package org.harvanir.pattern.clean.item.core.usecase.findpaginate;

import org.springframework.data.domain.Pageable;

/** @author Harvan Irsyadi */
public interface ItemFindPaginationUseCase {

  void findPagination(Pageable pageable, ItemFindPaginationPresenter presenter);
}
