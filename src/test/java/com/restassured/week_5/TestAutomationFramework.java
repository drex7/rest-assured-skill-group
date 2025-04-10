package com.restassured.week_5;

import com.restassured.BaseTest;
import data_providers.JSONPlaceholder;
import io.restassured.RestAssured;
import models.Post;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.*;

public class TestAutomationFramework extends BaseTest {


	static String URL = "https://jsonplaceholder.typicode.com/";

	@DataProvider
	Object[][] getPostIds() {
		return new Object[][] {
			{1},
			{8},
			{3},
			{5},
		};
	}

	@Test(dataProvider = "getPostIds") // here: DataProvider class is not required
	public void testUsingDataProviderInSameClass(int postId) {

		given().log().all()
			.baseUri(URL)
			.get( "posts/" + postId)
			.then().log().ifValidationFails()
			.statusCode(200)
			.and()
			.body("id", equalTo(postId));
	}

	@Test(dataProvider = "post-id", dataProviderClass = JSONPlaceholder.class)
	public void testUsingDataProviderInSeparateClass(int postId) {

		given()
			.spec(reqSpecification)
			.pathParam("postId", postId)
			.get("/{postId}")   // BasePath was already specified in reqSpec: Resulting Request URI: https://jsonplaceholder.typicode.com/posts/1
			.then().log().all()
			.body("id", equalTo(postId));
	}


	@Test(dataProvider = "getPostData", dataProviderClass = JSONPlaceholder.class)
	public void testUsingDataProviderModel(Post postData) {


		given()
			.spec(reqSpecification)
			.body(postData)
			.post()   // BasePath was already specified in reqSpec: Resulting Request URI: https://jsonplaceholder.typicode.com/posts
			.then().log().all()
			.assertThat()
			.body(
				"title", equalTo(postData.title()),
				"body", equalTo(postData.body()));
	}
}
