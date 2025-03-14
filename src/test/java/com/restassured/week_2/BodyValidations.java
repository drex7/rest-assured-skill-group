package com.restassured.week_2;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class BodyValidations {

	static String URL = "https://api.github.com/";

	@Test
	void firstBodyValidation() {
		RestAssured.get(URL + "users/drex7")
			.then().log().ifError()
			.body("html_url", response -> endsWith("drex7"));

	}


	@Test
	void secondBodyValidation() {
		RestAssured.get(URL + "rate_limit")
			.then().log().all()
			.body("resources.core.limit", response -> equalTo(response.jsonPath().get("rate.limit"))); //, equalTo("5"));

	}


	@Test
	void schemaValidation() {
		RestAssured.get(URL + "users/drex7")
			.then().log().ifError()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));

	}
}
