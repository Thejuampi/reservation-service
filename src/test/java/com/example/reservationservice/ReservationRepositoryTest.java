package com.example.reservationservice;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;

    @Test
    void testRepositoryShouldSaveFind() {
        val foundReservations = reservationRepository
                .save(new Reservation(null, "Juan"))
                .thenMany(reservationRepository.findByReservationName("Juan"));

        StepVerifier.create(foundReservations)
                .expectNextMatches(r -> r.getReservationName().equals("Juan"))
                .verifyComplete();
    }

}
