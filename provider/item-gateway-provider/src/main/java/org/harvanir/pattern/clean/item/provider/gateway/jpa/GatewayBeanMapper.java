package org.harvanir.pattern.clean.item.provider.gateway.jpa;

import org.harvanir.pattern.clean.item.core.entity.CreateItemRequest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.provider.gateway.jpa.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/** @author Harvan Irsyadi */
@Mapper(componentModel = "spring")
public interface GatewayBeanMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "orderItems", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "version", ignore = true)
  Item map(CreateItemRequest request);

  ItemResponse map(Item item);
}
