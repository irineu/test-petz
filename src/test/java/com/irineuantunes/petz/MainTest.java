package com.irineuantunes.petz;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void listCustomer(){
        RestAssured.when().get("/customer/").then().statusCode(200);
    }

    @Test
    public void getCustomer() {
        RestAssured.when().get("/customer/1").then().statusCode(200);
        RestAssured.when().get("/customer/10").then().statusCode(404);
    }

    @Test
    public void addCustomer() {
        RestAssured.given().contentType(ContentType.JSON).body("{\"firstName\": \"Test\", \"lastName\": \"test\"}")
                .post("/customer/").then().statusCode(200);
    }

    @Test
    public void changeCustomer() {
        RestAssured.given().contentType(ContentType.JSON).body("{\"firstName\": \"Test\", \"lastName\": \"test\"}")
                .patch("/customer/1").then().statusCode(200);
        RestAssured.given().contentType(ContentType.JSON).body("{\"firstName\": \"Test\", \"lastName\": \"test\"}")
                .patch("/customer/10").then().statusCode(404);
    }

    @Test
    public void deleteCustomer() {
        Map<String, Object> entry =  RestAssured.given().contentType(ContentType.JSON).body("{\"firstName\": \"Test\", \"lastName\": \"test\"}")
                .post("/customer/").then().statusCode(200).extract().response().as(Map.class);

        RestAssured.when().delete("/customer/"+ entry.get("id")).then().statusCode(200);
        RestAssured.when().delete("/customer/"+ entry.get("id")).then().statusCode(404);
    }

    @Test
    public void listPets(){
        RestAssured.when().get("/customer/1/pet/").then().statusCode(200);
        RestAssured.when().get("/customer/2/pet/").then().statusCode(404);
    }

    @Test
    public void addPet() {
        RestAssured.given().contentType(ContentType.JSON).body("{\"name\": \"Estela\"}")
                .post("/customer/1/pet/").then().statusCode(200);

        RestAssured.given().contentType(ContentType.JSON).body("{\"name\": \"Estela\"}")
                .post("/customer/2/pet/").then().statusCode(404);
    }

    @Test
    public void changePet() {

        List<Map> pets = RestAssured.when().get("/customer/1/pet/").then().statusCode(200).extract().response().getBody().as(List.class);

        RestAssured.given().contentType(ContentType.JSON).body("{\"name\": \"Test\"}")
                .patch("/customer/1/pet/"+pets.get(0).get("id")).then().statusCode(200);
        RestAssured.given().contentType(ContentType.JSON).body("{\"firstName\": \"Test\", \"lastName\": \"test\"}")
                .patch("/customer/2/pet/"+pets.get(0).get("id")).then().statusCode(404);
        RestAssured.given().contentType(ContentType.JSON).body("{\"firstName\": \"Test\", \"lastName\": \"test\"}")
                .patch("/customer/1/pet/10").then().statusCode(404);
    }

    @Test
    public void deletePet() {
        List<Map> pets = RestAssured.when().get("/customer/1/pet/").then().statusCode(200).extract().response().getBody().as(List.class);

        RestAssured.when().delete("/customer/1/pet/"+ pets.get(0).get("id")).then().statusCode(200);
        RestAssured.when().delete("/customer/2/pet/"+ pets.get(0).get("id")).then().statusCode(404);
        RestAssured.when().delete("/customer/1/pet/2").then().statusCode(404);
    }
}
