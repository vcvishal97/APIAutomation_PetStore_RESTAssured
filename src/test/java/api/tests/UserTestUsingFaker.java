package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndpoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTestUsingFaker {

	private User payload;
	private Faker faker;
	private Response response;
	
	@Test(priority = 0)
	public void testCreateUser() throws Exception {
		response = UserEndpoints.createUser(preparePayload());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 1)
	public void testGetUser() throws Exception {
		response = UserEndpoints.getUser(payload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2)
	public void testDeleteUser() throws Exception {
		response = UserEndpoints.deleteUser(payload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	private User preparePayload() {
		payload = new User();
		faker = new Faker();
		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		payload.setPassword(faker.internet().password());
		payload.setPhone(faker.phoneNumber().toString());
		payload.setUserStatus(0);
		return payload;
	}
}
