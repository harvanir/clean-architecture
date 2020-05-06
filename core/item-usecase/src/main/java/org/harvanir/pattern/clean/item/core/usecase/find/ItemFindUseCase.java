package org.harvanir.pattern.clean.item.core.usecase.find;

/** @author Harvan Irsyadi */
public interface ItemFindUseCase {

  void find(Long id, ItemFindPresenter presenter);
}
