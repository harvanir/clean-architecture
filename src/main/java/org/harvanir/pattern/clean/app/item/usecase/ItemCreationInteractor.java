package org.harvanir.pattern.clean.app.item.usecase;

import org.harvanir.pattern.clean.app.item.entity.CreateItemRequest;

/** @author Harvan Irsyadi */
public interface ItemCreationInteractor {

  void execute(CreateItemRequest request, ItemCreationPresenter presenter);
}
