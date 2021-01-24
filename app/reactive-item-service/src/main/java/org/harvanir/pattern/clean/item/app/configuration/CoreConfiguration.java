package org.harvanir.pattern.clean.item.app.configuration;

import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;
import org.harvanir.pattern.clean.item.core.usecase.create.DefaultItemCreationUseCase;
import org.harvanir.pattern.clean.item.core.usecase.create.ItemCreationUseCase;
import org.harvanir.pattern.clean.item.core.usecase.find.DefaultItemFindUseCase;
import org.harvanir.pattern.clean.item.core.usecase.find.DefaultItemFindWithDelayUseCase;
import org.harvanir.pattern.clean.item.core.usecase.find.ItemFindUseCase;
import org.harvanir.pattern.clean.item.core.usecase.find.ItemFindWithDelayUseCase;
import org.harvanir.pattern.clean.item.core.usecase.increase.DefaultItemIncreaseUseCase;
import org.harvanir.pattern.clean.item.core.usecase.increase.ItemIncreaseUseCase;
import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.GatewayBeanMapper;
import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.ItemGatewayR2dbc;
import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.repository.ItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;

/** @author Harvan Irsyadi */
@Configuration(proxyBeanMethods = false)
public class CoreConfiguration {

  @Bean
  public ItemGateway itemGateway(
      GatewayBeanMapper gatewayBeanMapper,
      ItemRepository itemRepository,
      DatabaseClient databaseClient) {
    return new ItemGatewayR2dbc(gatewayBeanMapper, itemRepository, databaseClient);
  }

  @Bean
  public ItemCreationUseCase itemCreationUseCase(ItemGateway itemGateway) {
    return new DefaultItemCreationUseCase(itemGateway);
  }

  @Bean
  public ItemFindUseCase itemFindUseCase(ItemGateway itemGateway) {
    return new DefaultItemFindUseCase(itemGateway);
  }

  @Bean
  public ItemFindWithDelayUseCase itemFindWithDelayUseCase(ItemGateway itemGateway) {
    return new DefaultItemFindWithDelayUseCase(itemGateway);
  }

  @Bean
  public ItemIncreaseUseCase itemIncreaseUseCase(ItemGateway itemGateway) {
    return new DefaultItemIncreaseUseCase(itemGateway);
  }
}
