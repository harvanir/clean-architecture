package org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter.common;

import reactor.core.publisher.Mono;

import java.util.function.Supplier;

/**
 * Delegate to this class for invoking concrete presenter.
 *
 * @author Harvan Irsyadi
 */
public class PresenterUtils {

  private static final Mono<Void> PRESENT = Mono.just(true).then();

  private PresenterUtils() {}

  public static Mono<Void> present(ResponseConstructor constructor) {
    constructor.construct();

    return PRESENT;
  }

  public static <T> Mono<T> getResponse(Supplier<T> input) {
    return Mono.fromSupplier(input);
  }
}
