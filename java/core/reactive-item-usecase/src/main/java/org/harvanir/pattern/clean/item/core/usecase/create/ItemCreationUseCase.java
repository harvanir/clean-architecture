package org.harvanir.pattern.clean.item.core.usecase.create;

import org.harvanir.pattern.clean.item.core.entity.CreateItemRequest;
import reactor.core.publisher.Mono;

/** @author Harvan Irsyadi */
public interface ItemCreationUseCase {

  Mono<Void> execute(CreateItemRequest request, ItemCreationPresenter presenter);
}
