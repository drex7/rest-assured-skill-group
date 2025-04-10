package com.restassured.week_4;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.internal.mapping.Jackson2Mapper;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertEquals;

public class Groovy {


	static String URL = "https://api.github.com/";

	@Test
	void secondBodyValidation() {

		String username = "drex7";

		RestAssured.get(URL + "search/repositories?q=java")
			.then().log().all()
			.body("items.findAll { it.language == 'Java' }.size()", greaterThan(1));
	}

	@Test
	public void objectMappingTest() {
		RateLimit rateLimit = RestAssured.get(URL + "rate_limit").as(RateLimit.class, getObjectMapper());
		assertEquals(rateLimit.getCoreLimit(), 60);
		assertEquals(rateLimit.getUsed(), 60);
		assertEquals(rateLimit.getCoreLimit(), 60);
	}

	public static Jackson2Mapper getObjectMapper() {
		return new Jackson2Mapper((type, s) -> {
			ObjectMapper om = new ObjectMapper();
			om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return om;
		});
	}
}
