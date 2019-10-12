package com.example.reservationservice;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class ReservationDocumentTest {

    @Autowired
    ReactiveMongoTemplate template;

    @Test
    void testShouldStoreAndRetrieve() {
        val publisher = template.save(new Reservation(null, "PABLO"));

        StepVerifier.create(publisher)
                .expectNextMatches(r -> r.getReservationName().equals("PABLO"))
                .verifyComplete();
    }

}
