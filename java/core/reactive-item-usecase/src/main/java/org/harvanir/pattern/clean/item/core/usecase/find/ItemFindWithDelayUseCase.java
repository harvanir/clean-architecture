package org.harvanir.pattern.clean.item.core.usecase.find;

import org.harvanir.pattern.clean.item.core.entity.FindWithDelayRequest;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public interface ItemFindWithDelayUseCase {

  Mono<Void> execute(FindWithDelayRequest request, ItemFindPresenter presenter);
}
