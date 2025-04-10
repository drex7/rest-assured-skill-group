package com.restassured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

	public RequestSpecification reqSpecification;

	@BeforeSuite
	public void setUp() {
		RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
		reqSpecBuilder.setBaseUri("https://jsonplaceholder.typicode.com");
		reqSpecBuilder.setBasePath("/posts");
		reqSpecBuilder.addHeader("content-type", "application/json");
		reqSpecBuilder.log(LogDetail.ALL);

		reqSpecification = reqSpecBuilder.build();
	}
}
