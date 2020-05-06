package org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter;

import org.harvanir.pattern.clean.item.app.entrypoint.v1.entity.ItemResponseRest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.mapstruct.Mapper;

/** @author Harvan Irsyadi */
@Mapper(componentModel = "spring")
public interface BeanMapper {

  ItemResponseRest mapToItemResponse(ItemResponse itemResponse);
}
