package org.harvanir.pattern.clean.app.item.configuration;

import org.harvanir.pattern.clean.app.item.driver.jpa.repository.ItemJpaRepository;
import org.harvanir.pattern.clean.app.item.gateway.ItemGateway;
import org.harvanir.pattern.clean.app.item.provider.gateway.GatewayBeanMapper;
import org.harvanir.pattern.clean.app.item.provider.gateway.ItemGatewayJpa;
import org.harvanir.pattern.clean.app.item.usecase.ItemCreationInteractor;
import org.harvanir.pattern.clean.app.item.usecase.ItemCreationInteractorImpl;
import org.harvanir.pattern.clean.app.item.usecase.ItemFindInteractor;
import org.harvanir.pattern.clean.app.item.usecase.ItemFindInteractorImpl;
import org.harvanir.pattern.clean.app.item.usecase.ItemFindPaginationInteractor;
import org.harvanir.pattern.clean.app.item.usecase.ItemFindPaginationInteractorImpl;
import org.harvanir.pattern.clean.app.item.usecase.ItemIncreaseInteractor;
import org.harvanir.pattern.clean.app.item.usecase.ItemIncreaseInteractorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author Harvan Irsyadi */
@Configuration
public class ItemConfiguration {

  @Bean
  public ItemGateway itemGateway(
      GatewayBeanMapper gatewayBeanMapper, ItemJpaRepository itemJpaRepository) {
    return new ItemGatewayJpa(gatewayBeanMapper, itemJpaRepository);
  }

  @Bean
  public ItemFindInteractor itemFindInteractor(ItemGateway itemGateway) {
    return new ItemFindInteractorImpl(itemGateway);
  }

  @Bean
  public ItemFindPaginationInteractor itemFindPaginationInteractor(ItemGateway itemGateway) {
    return new ItemFindPaginationInteractorImpl(itemGateway);
  }

  @Bean
  public ItemCreationInteractor itemCreationInteractor(ItemGateway itemGateway) {
    return new ItemCreationInteractorImpl(itemGateway);
  }

  @Bean
  public ItemIncreaseInteractor itemIncreaseInteractor(ItemGateway itemGateway) {
    return new ItemIncreaseInteractorImpl(itemGateway);
  }
}
