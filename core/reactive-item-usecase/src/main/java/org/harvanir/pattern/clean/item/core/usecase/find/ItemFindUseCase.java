package org.harvanir.pattern.clean.item.core.usecase.find;

import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public interface ItemFindUseCase {

  Mono<Void> execute(Long id, ItemFindPresenter presenter);
}
