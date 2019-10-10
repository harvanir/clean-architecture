package org.harvanir.pattern.clean.app.item.usecase;

import org.harvanir.pattern.clean.app.item.entity.ItemResponse;

/** @author Harvan Irsyadi */
public interface ItemCreationPresenter {

  void execute(ItemResponse response);
}
