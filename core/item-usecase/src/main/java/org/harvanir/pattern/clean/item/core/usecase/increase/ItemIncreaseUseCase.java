package org.harvanir.pattern.clean.item.core.usecase.increase;

import org.harvanir.pattern.clean.item.core.entity.ItemIncreaseRequest;

/** @author Harvan Irsyadi */
public interface ItemIncreaseUseCase {

  void increase(ItemIncreaseRequest request, ItemIncreasePresenter presenter);
}
