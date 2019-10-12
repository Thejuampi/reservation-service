package com.example.reservationservice;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReservationPojoTest {

    @Test
    void testShouldConstruct() {
        val reservation = new Reservation("1", "Juan");

        assertThat(reservation.getId()).isEqualTo("1");
        assertThat(reservation.getReservationName()).isEqualToIgnoringCase("juan");

        assertThat(reservation)
                .as("not a null reference")
                .isNotNull();

        assertThat(reservation.getReservationName())
                .as("A name is populated")
                .isNotBlank();
    }

}
