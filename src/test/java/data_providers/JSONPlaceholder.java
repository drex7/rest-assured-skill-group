package data_providers;

import models.Post;
import org.testng.annotations.DataProvider;

public class JSONPlaceholder {

	@DataProvider(name = "post-id")
	public Object[][] getPostId() {
		return new Object[][] {
			{1},
			{2},
			{3}
		};
	}

	@DataProvider // name param can be omitted, in that case method name is used
	public Object[][] getPostData() {
		return new Object[][] {
			{ new Post("Summary", "This is the summary", 5) },
			{ new Post("Class Load Failure", "Failed to load class", 4) }
		};
	}
}
