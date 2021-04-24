package org.harvanir.pattern.clean.item.app.entrypoint.v1.controller;

import org.harvanir.pattern.clean.item.app.BaseTestConfiguration;
import org.harvanir.pattern.clean.item.core.entity.CreateItemRequest;
import org.harvanir.pattern.clean.item.core.entity.ItemResponse;
import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.model.Item;
import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/** @author Harvan Irsyadi */
@Import(BaseTestConfiguration.class)
@WebFluxTest
class ItemQueryControllerV1Test {

  @Autowired private WebTestClient webTestClient;

  @MockBean private ItemRepository itemRepository;

  @MockBean private DatabaseClient databaseClient;

  @AfterEach
  void afterEach() {
    verifyNoMoreInteractions(itemRepository, databaseClient);
  }

  private String getLocalDateTimeString(LocalDateTime localDateTime) {
    String localDateTimeString =
        DateTimeFormatter.ISO_DATE_TIME
            .format(
                ZonedDateTime.of(localDateTime, ZoneId.systemDefault())
                    .toOffsetDateTime()
                    .withOffsetSameInstant(ZoneOffset.UTC))
            .replace("Z", "");

    return StringUtils.padZeroRight(localDateTimeString, 23) + "+00:00";
  }

  @Test
  void findById() {
    CreateItemRequest body =
        CreateItemRequest.builder()
            .name(ItemCommandControllerV1Test.class.getName())
            .price(BigDecimal.TEN)
            .quantity(10)
            .build();
    LocalDateTime now = LocalDateTime.now();
    Long id = 1L;
    Item item =
        Item.builder()
            .id(id)
            .name(body.getName())
            .price(body.getPrice())
            .quantity(body.getQuantity())
            .createdAt(now)
            .updatedAt(now)
            .version(1)
            .build();

    when(itemRepository.findById(id)).thenReturn(Mono.just(item));

    String nowString = getLocalDateTimeString(now);

    webTestClient
        .get()
        .uri(ApiPathV1.V1_ITEMS + String.format("/%s", id))
        .exchange()
        .expectStatus()
        .is2xxSuccessful()
        .expectBody()
        .jsonPath("$.id")
        .isEqualTo(id)
        .jsonPath("$.name")
        .isEqualTo(body.getName())
        .jsonPath("$.quantity")
        .isEqualTo(body.getQuantity())
        .jsonPath("$.price")
        .isEqualTo(body.getPrice())
        .jsonPath("$.createdAt")
        .isEqualTo(nowString)
        .jsonPath("$.updatedAt")
        .isEqualTo(nowString);

    verify(itemRepository, times(1)).findById(any(Long.class));
  }

  @SuppressWarnings("unchecked")
  @Test
  void findByIdAndDelaySeconds() {
    CreateItemRequest body =
        CreateItemRequest.builder()
            .name(ItemCommandControllerV1Test.class.getName())
            .price(BigDecimal.TEN)
            .quantity(10)
            .build();
    LocalDateTime now = LocalDateTime.now();
    Long id = 1L;
    Integer delaySeconds = 2;
    ItemResponse item =
        ItemResponse.builder()
            .id(id)
            .name(body.getName())
            .price(body.getPrice())
            .quantity(body.getQuantity())
            .createdAt(now)
            .updatedAt(now)
            .build();
    DatabaseClient.GenericExecuteSpec spec = Mockito.mock(DatabaseClient.GenericExecuteSpec.class);
    RowsFetchSpec<ItemResponse> rowFetchSpec = Mockito.mock(RowsFetchSpec.class);

    when(rowFetchSpec.one()).thenReturn(Mono.just(item));
    when(spec.bind(0, id)).thenReturn(spec);
    when(spec.map(any(Function.class))).thenReturn(rowFetchSpec);
    when(databaseClient.sql(anyString())).thenReturn(spec);

    String nowString = getLocalDateTimeString(now);

    webTestClient
        .get()
        .uri(ApiPathV1.V1_ITEMS + String.format("/%s/%s", id, delaySeconds))
        .exchange()
        .expectStatus()
        .is2xxSuccessful()
        .expectBody()
        .jsonPath("$.id")
        .isEqualTo(id)
        .jsonPath("$.name")
        .isEqualTo(body.getName())
        .jsonPath("$.quantity")
        .isEqualTo(body.getQuantity())
        .jsonPath("$.price")
        .isEqualTo(body.getPrice())
        .jsonPath("$.createdAt")
        .isEqualTo(nowString)
        .jsonPath("$.updatedAt")
        .isEqualTo(nowString);

    verify(databaseClient, times(1)).sql(any(String.class));
  }
}
