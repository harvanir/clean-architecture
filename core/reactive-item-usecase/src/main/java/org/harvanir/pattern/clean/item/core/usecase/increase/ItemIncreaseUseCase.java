package org.harvanir.pattern.clean.item.core.usecase.increase;

import org.harvanir.pattern.clean.item.core.entity.ItemIncreaseRequest;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public interface ItemIncreaseUseCase {

  Mono<Void> execute(ItemIncreaseRequest request, ItemIncreasePresenter presenter);
}
