package api.endpoints;

import static io.restassured.RestAssured.given;
import api.payloads.User;
import api.utilities.PropertyReader;
import io.restassured.response.Response;

public class UserEndpoints {

	public static boolean ROUTES_FROM_PROPERTIES = false;
	private static PropertyReader propertyReader = new PropertyReader();
	
	public static Response createUser(User payload) throws Exception {
		return given().accept("application/json").contentType("application/json").body(payload)
				.when().post(getPostURL());
	}
	
	public static Response getUser(String username) throws Exception {
		return given().pathParam("username", username)
				.when().get(getGetURL());
	}
	
	public static Response deleteUser(String username) throws Exception {
		return given().contentType("application/json").pathParam("username", username)
				.when().delete(getDeleteURL());
	}
	
	private static String getPostURL() {
		return (ROUTES_FROM_PROPERTIES) ? propertyReader.readProperty("postURL") : Routes.postURL;
	}
	
	private static String getGetURL() {
		return (ROUTES_FROM_PROPERTIES) ? propertyReader.readProperty("getURL") : Routes.getURL; 
	}
	
	private static String getDeleteURL() {
		return (ROUTES_FROM_PROPERTIES) ? propertyReader.readProperty("deleteURL") : Routes.deleteURL;
	}
}
