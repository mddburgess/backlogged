package com.metricalsky.backlogged.backend.it.api;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TitlesApiIT {

    @LocalServerPort
    private int port;

    @Test
    void list() {
        RestAssured.given()
                .port(port)
                .when()
                .get("/api/titles")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("$", is(empty()));
    }
}
