package org.harvanir.pattern.clean.item.app.entrypoint.v1.presenter;

import org.harvanir.pattern.clean.item.app.entrypoint.v1.entity.ItemResponseRest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/** @author Harvan Irsyadi */
@Mapper(componentModel = "spring")
public interface BeanMapper {

  default Date fromLocalDateTime(LocalDateTime localDateTime) {
    if (localDateTime == null) {
      return null;
    }
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  @Mapping(target = "toBeFiltered")
  ItemResponseRest mapToItemResponse(ItemResponse itemResponse);
}
