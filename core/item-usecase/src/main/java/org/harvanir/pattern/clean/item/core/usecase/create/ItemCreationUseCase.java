package org.harvanir.pattern.clean.item.core.usecase.create;

import org.harvanir.pattern.clean.item.core.entity.CreateItemRequest;

/** @author Harvan Irsyadi */
public interface ItemCreationUseCase {

  void execute(CreateItemRequest request, ItemCreationPresenter presenter);
}
