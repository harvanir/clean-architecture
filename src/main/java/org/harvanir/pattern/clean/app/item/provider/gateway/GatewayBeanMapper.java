package org.harvanir.pattern.clean.app.item.provider.gateway;

import org.harvanir.pattern.clean.app.item.driver.jpa.model.Item;
import org.harvanir.pattern.clean.app.item.entity.CreateItemRequest;
import org.harvanir.pattern.clean.app.item.entity.ItemResponse;
import org.mapstruct.Mapper;

/** @author Harvan Irsyadi */
@Mapper(componentModel = "spring")
public interface GatewayBeanMapper {

  Item map(CreateItemRequest request);

  ItemResponse map(Item item);
}
