package org.harvanir.pattern.clean.app.item.usecase;

/** @author Harvan Irsyadi */
public interface ItemFindInteractor {

  void find(Long id, ItemFindPresenter presenter);
}
