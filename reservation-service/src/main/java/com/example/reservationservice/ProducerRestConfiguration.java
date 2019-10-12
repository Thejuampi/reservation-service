package com.example.reservationservice;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@RequiredArgsConstructor
class ProducerRestConfiguration {

  private final ReservationRepository repo;

  @Bean
  RouterFunction<ServerResponse> routes() {
    return RouterFunctions.route(GET("/reservations"),
        r -> ok().body(repo.findAll(), Reservation.class)
    );
  }

}
