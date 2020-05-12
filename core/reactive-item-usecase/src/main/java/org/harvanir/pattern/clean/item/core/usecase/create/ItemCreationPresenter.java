package org.harvanir.pattern.clean.item.core.usecase.create;

import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public interface ItemCreationPresenter {

  Mono<Void> execute(ItemResponse response);
}
