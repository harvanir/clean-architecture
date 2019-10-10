package org.harvanir.pattern.clean.app.item.usecase;

import org.harvanir.pattern.clean.app.item.entity.ItemResponse;
import org.springframework.data.domain.Page;

/** @author Harvan Irsyadi */
public interface ItemFindPaginationPresenter {

  void present(Page<ItemResponse> responses);
}
