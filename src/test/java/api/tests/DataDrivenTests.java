package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import api.endpoints.UserEndpoints;
import api.payloads.User;
import io.restassured.response.Response;

public class DataDrivenTests {

	private User payload;
	private Response response;
	private Logger logger;
	
	@BeforeTest
	void before() {
		logger = LogManager.getLogger(this.getClass());
		UserEndpoints.ROUTES_FROM_PROPERTIES = false;
		logger.info("*** test started ***");
	}
	
	@AfterTest
	void after() {
		logger.info("*** test completed ***");
	}
	
	@Test(priority = 0, dataProvider = "userData", dataProviderClass = api.utilities.DataProviders.class)
	public void testCreateUsers(String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus) throws Exception {
		preparePayload(id, username, firstName, lastName, email, password, phone, userStatus);
		logger.info("*** creating user ***");
		response = UserEndpoints.createUser(payload);
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*** created user ***");
	}
	
	@Test(priority = 1, dataProvider = "usernames", dataProviderClass = api.utilities.DataProviders.class)
	public void testGetUsers(String username) throws Exception {
		logger.info("*** getting user ***");
		response = UserEndpoints.getUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*** got user ***");
	}

	@Test(priority = 2, dataProvider = "usernames", dataProviderClass = api.utilities.DataProviders.class)
	public void testDeleteUsers(String username) throws Exception {
		logger.info("*** deleting user ***");
		response = UserEndpoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*** deleted user ***");
	}
	
	private void preparePayload(String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus) {
		logger.info("*** preparing payload ***");
		payload = new User();
		payload.setId(Integer.parseInt(id));
		payload.setUsername(username);
		payload.setFirstName(firstName);
		payload.setLastName(lastName);
		payload.setEmail(email);
		payload.setPassword(password);
		payload.setPhone(phone);
		payload.setUserStatus(Integer.parseInt(userStatus));
	}
}
