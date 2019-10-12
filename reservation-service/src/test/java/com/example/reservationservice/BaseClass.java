package com.example.reservationservice;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;

@Import(ProducerRestConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "server.port=0")
@ExtendWith(SpringExtension.class)
public class BaseClass {

  @MockBean
  ReservationRepository repo;

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.baseURI = "http://localhost:" + this.port;
    Mockito.when(repo.findAll())
        .thenReturn(Flux.just(new Reservation("1", "Juan")));
  }

}
