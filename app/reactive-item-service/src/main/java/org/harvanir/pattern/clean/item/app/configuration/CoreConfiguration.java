package org.harvanir.pattern.clean.item.app.configuration;

import org.harvanir.pattern.clean.item.core.gateway.ItemGateway;
import org.harvanir.pattern.clean.item.core.usecase.create.DefaultItemCreationUseCase;
import org.harvanir.pattern.clean.item.core.usecase.create.ItemCreationUseCase;
import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.GatewayBeanMapper;
import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.ItemGatewayR2dbc;
import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.repository.ItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author Harvan Irsyadi */
@Configuration(proxyBeanMethods = false)
public class CoreConfiguration {

  @Bean
  public ItemGateway itemGateway(
      GatewayBeanMapper gatewayBeanMapper, ItemRepository itemRepository) {
    return new ItemGatewayR2dbc(gatewayBeanMapper, itemRepository);
  }

  @Bean
  public ItemCreationUseCase itemCreationUseCase(ItemGateway itemGateway) {
    return new DefaultItemCreationUseCase(itemGateway);
  }
}
