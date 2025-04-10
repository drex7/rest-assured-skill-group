package com.restassured.week_4;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class RateLimit {

	/*
	"resources": {
	    "core": {
	      "limit": 60,
	      "remaining": 42,
	      "reset": 1742569188,
	      "used": 18,
	      "resource": "core"
	    },
    }
	 */


	@JsonProperty("resources")
	public void getResources(Map<String, Object> resources) {
		var core = (Map<String, Integer>)resources.get("core");
		coreLimit = core.get("limit");

		used = core.get("used");

		remaining = core.get("remaining");



	}

	private int coreLimit;
	private int remaining;
	private int used;

	public int getCoreLimit() {
		return coreLimit;
	}

	public int getRemaining() {
		return remaining;
	}

	public int getUsed() {
		return used;
	}
}
