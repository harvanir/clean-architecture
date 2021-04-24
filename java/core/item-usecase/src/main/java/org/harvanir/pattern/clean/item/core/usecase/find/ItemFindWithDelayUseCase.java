package org.harvanir.pattern.clean.item.core.usecase.find;

import org.harvanir.pattern.clean.item.core.entity.FindWithDelayRequest;

/** @author Harvan Irsyadi */
public interface ItemFindWithDelayUseCase {

  void execute(FindWithDelayRequest request, ItemFindPresenter presenter);
}
