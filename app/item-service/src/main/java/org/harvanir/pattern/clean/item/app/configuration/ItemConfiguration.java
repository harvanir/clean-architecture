package org.harvanir.pattern.clean.item.app.configuration;

import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;
import org.harvanir.pattern.clean.item.core.usecase.create.DefaultItemCreationUseCase;
import org.harvanir.pattern.clean.item.core.usecase.create.ItemCreationUseCase;
import org.harvanir.pattern.clean.item.core.usecase.find.DefaultItemFindUseCase;
import org.harvanir.pattern.clean.item.core.usecase.find.ItemFindUseCase;
import org.harvanir.pattern.clean.item.core.usecase.findpaginate.DefaultItemFindPaginationUseCase;
import org.harvanir.pattern.clean.item.core.usecase.findpaginate.ItemFindPaginationUseCase;
import org.harvanir.pattern.clean.item.core.usecase.increase.DefaultItemIncreaseUseCase;
import org.harvanir.pattern.clean.item.core.usecase.increase.IncreaseExceptionChecker;
import org.harvanir.pattern.clean.item.core.usecase.increase.ItemIncreaseUseCase;
import org.harvanir.pattern.clean.item.provider.gateway.jpa.GatewayBeanMapper;
import org.harvanir.pattern.clean.item.provider.gateway.jpa.ItemGatewayJpa;
import org.harvanir.pattern.clean.item.provider.gateway.jpa.repository.ItemJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author Harvan Irsyadi */
@Configuration(proxyBeanMethods = false)
public class ItemConfiguration {

  @Bean
  public ItemGateway itemGateway(
      GatewayBeanMapper gatewayBeanMapper, ItemJpaRepository itemJpaRepository) {
    return new ItemGatewayJpa(gatewayBeanMapper, itemJpaRepository);
  }

  @Bean
  public ItemFindUseCase itemFindUseCase(ItemGateway itemGateway) {
    return new DefaultItemFindUseCase(itemGateway);
  }

  @Bean
  public ItemFindPaginationUseCase itemFindPaginationUseCase(ItemGateway itemGateway) {
    return new DefaultItemFindPaginationUseCase(itemGateway);
  }

  @Bean
  public ItemCreationUseCase itemCreationUseCase(ItemGateway itemGateway) {
    return new DefaultItemCreationUseCase(itemGateway);
  }

  @Bean
  public IncreaseExceptionChecker increaseExceptionChecker() {
    return new IncreaseExceptionChecker();
  }

  @Bean
  public ItemIncreaseUseCase itemIncreaseUseCase(ItemGateway itemGateway) {
    return new DefaultItemIncreaseUseCase(itemGateway);
  }
}
