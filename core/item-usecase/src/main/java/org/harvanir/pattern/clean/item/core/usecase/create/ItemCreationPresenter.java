package org.harvanir.pattern.clean.item.core.usecase.create;

import org.harvanir.pattern.clean.item.core.entity.ItemResponse;

/** @author Harvan Irsyadi */
public interface ItemCreationPresenter {

  void execute(ItemResponse response);
}
