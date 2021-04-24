package org.harvanir.pattern.clean.item.core.usecase.findpaginate;

import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.springframework.data.domain.Page;

/** @author Harvan Irsyadi */
public interface ItemFindPaginationPresenter {

  void present(Page<ItemResponse> responses);
}
