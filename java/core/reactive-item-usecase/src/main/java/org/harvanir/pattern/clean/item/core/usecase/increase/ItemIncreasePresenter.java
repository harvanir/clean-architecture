package org.harvanir.pattern.clean.item.core.usecase.increase;

import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public interface ItemIncreasePresenter {

  Mono<Void> present(ItemResponse response);
}
