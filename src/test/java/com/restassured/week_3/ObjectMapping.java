package com.restassured.week_3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ObjectMapping {
	public static void main(String[] args) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		BankAccount bankAccount = new BankAccount(10, "Derrick");

		// Serialization: java object to json
		objectMapper.writeValue(new File("src/main/resources/bank.json"), bankAccount);

		String asString = objectMapper.writeValueAsString(bankAccount);
		System.out.println(asString);

		BankAccount ba1 = objectMapper.readValue(
			new URL("file:src/main/resources/bank.json"),
			BankAccount.class
		);

		//	Deserialization: parse json into java object
		String jsonString = """
			{
				"id": 123,
				"holderName": "Daniel"
			}
			""";
//		ObjectMapper objectMapper = new ObjectMapper();
		BankAccount ba = objectMapper.readValue(jsonString, BankAccount.class);

		System.out.println(ba);

		// JsonNode: Retrieve a specific field, and not the whole document
		JsonNode jsonRootNode = objectMapper.readTree(jsonString);
		System.out.println(jsonRootNode.get("holderName").asText());


		String jsonStringArray = """
			[
				{
				"id": 123,
				"holderName": "Daniel"
				},
				{
				"id": 124,
				"holderName": "Dominique"
				},
				{
				"id": 125,
				"holderName": "Polos"
				}
			]
			""";

		// Parsing into an Array
		objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
		BankAccount[] accounts = objectMapper.readValue(jsonStringArray, BankAccount[].class);

		// Parse into a List, using TypeReference
		objectMapper.readValue(jsonStringArray, new TypeReference<List<BankAccount>>() {});

		// Parsing into a Map
		objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
		});


		/**
		 * Customization:
		 * if json has new fields that isn't specified in the Java class, u can pass FAIL_ON_UNKNOWN_PROPERTIES
		 */

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JsonNode jsonRoot = objectMapper.readTree(jsonString);
		JsonNode jsonNode = jsonRoot.get("balance");
		String balance = jsonNode.asText();
	}
}

class BankAccount {

	BankAccount(int id, String holderName) {
		this.id = id;
		this.holderName = holderName;
	}

	private int id;
	private String holderName;
}