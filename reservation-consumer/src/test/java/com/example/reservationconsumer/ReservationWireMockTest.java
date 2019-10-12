package com.example.reservationconsumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@AutoConfigureStubRunner(ids = "com.example:reservation-service:+:8080", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@Import({ReservationConsumerApplication.class, ReservationClient.class})
class ReservationWireMockTest {

  @Autowired
  ReservationClient client;

  @Test
  void testShouldClientReturn() {
    StepVerifier.create(client.getAllReservations())
        .expectNextMatches(r -> r.getId() != null
            && r.getReservationName().equalsIgnoreCase("Juan"))
        .expectComplete()
        .log()
        .verify();
  }

}
