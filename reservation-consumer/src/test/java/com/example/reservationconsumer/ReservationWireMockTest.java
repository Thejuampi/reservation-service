package com.example.reservationconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.util.List;

@ExtendWith(SpringExtension.class)
@AutoConfigureWireMock(port = 8080)
//@AutoConfigureJsonTesters gives an object mapper, which gives the validation stage for the obj mapper. Object mapper is used to get data in and out
@AutoConfigureJsonTesters
@Import({ReservationConsumerApplication.class, ReservationClient.class})
class ReservationWireMockTest {

  @Autowired
  ReservationClient client;

  @Autowired
  ObjectMapper objectMapper;

  @SneakyThrows
  @BeforeEach
  void setUp() {
    val json = objectMapper.writeValueAsString(List.of(
        new Reservation("1", "PABLO")
    ));

    WireMock.stubFor(WireMock.get("/reservations")
        .willReturn(WireMock.aResponse()
            .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
            .withBody(json)));
  }

  @Test
  void testShouldClientReturn() {
    StepVerifier.create(client.getAllReservations())
        .expectNextMatches(r -> r.getId() != null
            && r.getName().equalsIgnoreCase("pablo"))
        .expectComplete()
        .log()
        .verify();
  }

}
