package com.example.reservationservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@WebFluxTest // To test in isolation
@Import(ProducerRestConfiguration.class)
@ExtendWith(SpringExtension.class)
class ReservationRestControllerTest {

  @MockBean
  ReservationRepository repo; // because we're testing in isolation, we need to mock this dependency

  @Autowired
  WebTestClient webTestClient;

  @Test
  void getAllReservations() {
    Mockito.when(repo.findAll())
        .thenReturn(Flux.just(new Reservation("1", "Mario")));

    this.webTestClient.get()
        .uri("/reservations")
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody().jsonPath("@.[0].reservationName").isEqualTo("Mario");

  }


}
