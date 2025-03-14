package com.restassured.week_2;


import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class HeaderValidations {

	static String URL = "https://api.github.com/rate_limit";

	@Test
	void firstHeaderValidation() {
		RestAssured.get(URL)
			.then()
			.header("x-ratelimit-limit", equalTo("60"))
			.header("x-ratelimit-limit", header -> Integer.parseInt(header), equalTo(60));

	}


	@Test
	void secondHeaderValidation() {
		RestAssured.get(URL)
			.then().log().all()
			.header("x-zcode-limit", response -> greaterThanOrEqualTo(response.header("X-RateLimit-Remaining")));

	}

}
