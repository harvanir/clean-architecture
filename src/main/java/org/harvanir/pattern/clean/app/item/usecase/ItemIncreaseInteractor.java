package org.harvanir.pattern.clean.app.item.usecase;

import org.harvanir.pattern.clean.app.item.entity.ItemIncreaseRequest;

/** @author Harvan Irsyadi */
public interface ItemIncreaseInteractor {

  void increase(ItemIncreaseRequest request, ItemIncreasePresenter presenter);
}
