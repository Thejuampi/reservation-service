package com.example.reservationconsumer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class ReservationClient {

  private final WebClient webClient;

  public Flux<Reservation> getAllReservations() {
    return webClient.get()
        .uri("http://localhost:8080/reservations") // don't do this in prod!
        .retrieve()
        .bodyToFlux(Reservation.class);
  }

}
