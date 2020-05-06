package org.harvanir.pattern.clean.item.core.usecase.increase;

import org.harvanir.pattern.clean.item.core.entity.ItemIncreaseRequest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

/** @author Harvan Irsyadi */
public class DefaultItemIncreaseUseCase implements ItemIncreaseUseCase {

  private final ItemGateway itemGateway;

  public DefaultItemIncreaseUseCase(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  @Retryable(
      exceptionExpression = "#{@increaseExceptionChecker.shouldRetry(#root)}",
      maxAttemptsExpression = "${app.retry.max-attempts}",
      backoff = @Backoff(delayExpression = "${app.retry.delay}"))
  @Override
  public void increase(ItemIncreaseRequest request, ItemIncreasePresenter presenter) {
    ItemResponse response = itemGateway.increase(request.getId(), request.getIncrement());

    presenter.present(response);
  }
}
