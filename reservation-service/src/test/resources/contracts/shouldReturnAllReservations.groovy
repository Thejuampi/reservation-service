package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description("should return all Reservations")

    // assuming it looks like this
    request {
        method(GET())
        url("/reservations")
    }

    // I should get data that looks like this
    response {
        status(200)
        headers {
            contentType(applicationJsonUtf8())
        }
        body("""
            [
                {"id": "1", "reservationName": "Juan" }
            ]
        """)
    }

}