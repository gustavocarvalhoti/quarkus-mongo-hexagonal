package org.desafio;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.desafio.model.domain.Wishlist;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WishlistControllerTest {

    @Test
    @Order(1)
    public void addWishlistOK() {
        Wishlist wishlist = Wishlist.builder()
                .customerId("C-TESTE")
                .productId("P-TESTE")
                .productName("Iphone 15")
                .productValue(BigDecimal.valueOf(150))
                .quantity(2)
                .build();

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(wishlist)
                .when()
                .post("/wishlist")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(204, response.statusCode());
    }

    @Test
    @Order(2)
    public void getListOK() {
        given()
                .when().get("/wishlist")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(3)
    public void getByCustomerOK() {
        Response response = given()
                .get("/wishlist/C-TESTE")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(4)
    public void getByCustomerAndProductOK() {
        given()
                .when().get("/wishlist/C-TESTE/P-TESTE")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(5)
    public void deleteOK() {
        given()
                .when().delete("/wishlist/C-TESTE/P-TESTE")
                .then()
                .statusCode(204);
    }

    @Test
    @Order(6)
    public void deleteParamNotFoundError() {
        given()
                .when().delete("/wishlist")
                .then()
                .statusCode(405);
    }

}