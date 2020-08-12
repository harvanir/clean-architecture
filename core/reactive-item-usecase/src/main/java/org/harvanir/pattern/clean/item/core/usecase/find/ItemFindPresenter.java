package org.harvanir.pattern.clean.item.core.usecase.find;

import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public interface ItemFindPresenter {

  Mono<Void> execute(ItemResponse response);
}
