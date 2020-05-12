package org.harvanir.pattern.clean.item.app.entrypoint.v1.controller;

import org.harvanir.pattern.clean.item.app.BaseTestConfiguration;
import org.harvanir.pattern.clean.item.core.entity.CreateItemRequest;
import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.model.Item;
import org.harvanir.pattern.clean.item.provider.gateway.r2dbc.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/** @author Harvan Irsyadi */
@Import(BaseTestConfiguration.class)
@WebFluxTest
class ItemCommandControllerV1Test {

  @Autowired private WebTestClient webTestClient;

  @MockBean private ItemRepository itemRepository;

  @AfterEach
  void afterEach() {
    verifyNoMoreInteractions(itemRepository);
  }

  @Test
  void createTest() {
    CreateItemRequest body =
        CreateItemRequest.builder()
            .name(ItemCommandControllerV1Test.class.getName())
            .price(BigDecimal.TEN)
            .quantity(10)
            .build();
    LocalDateTime now = LocalDateTime.now();
    Item item =
        Item.builder()
            .id(new Random().nextLong())
            .name(body.getName())
            .price(body.getPrice())
            .quantity(body.getQuantity())
            .createdAt(now)
            .updatedAt(now)
            .version(1)
            .build();
    when(itemRepository.save(any(Item.class))).thenReturn(Mono.just(item));

    webTestClient
        .post()
        .uri(ApiPathV1.V1_ITEMS)
        .body(Mono.just(body), CreateItemRequest.class)
        .exchange()
        .expectStatus()
        .is2xxSuccessful()
        .expectBody()
        .jsonPath("$.id")
        .isNotEmpty()
        .jsonPath("$.name")
        .isEqualTo(body.getName())
        .jsonPath("$.quantity")
        .isEqualTo(body.getQuantity())
        .jsonPath("$.price")
        .isEqualTo(body.getPrice())
        .jsonPath("$.createdAt")
        .isNotEmpty()
        .jsonPath("$.updatedAt")
        .isNotEmpty();

    verify(itemRepository, times(1)).save(any(Item.class));
  }
}
