package org.harvanir.pattern.clean.app.item.usecase;

import org.harvanir.pattern.clean.app.item.entity.ItemIncreaseRequest;
import org.harvanir.pattern.clean.app.item.entity.ItemResponse;
import org.harvanir.pattern.clean.app.item.gateway.ItemGateway;
import org.hibernate.StaleObjectStateException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

/** @author Harvan Irsyadi */
public class ItemIncreaseInteractorImpl implements ItemIncreaseInteractor {

  private final ItemGateway itemGateway;

  public ItemIncreaseInteractorImpl(ItemGateway itemGateway) {
    this.itemGateway = itemGateway;
  }

  @Retryable(
          value = {StaleObjectStateException.class, ObjectOptimisticLockingFailureException.class},
          maxAttemptsExpression = "${app.retry.max-attempts}",
          backoff = @Backoff(delayExpression = "${app.retry.delay}"))
  @Override
  public void increase(ItemIncreaseRequest request, ItemIncreasePresenter presenter) {
    ItemResponse response = itemGateway.increase(request.getId(), request.getIncrement());

    presenter.present(response);
  }
}
