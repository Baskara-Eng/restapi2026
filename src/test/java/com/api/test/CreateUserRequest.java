package com.api.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.constants.HTTPStatus;
import com.api.pojo.User;
import com.api.restclient.RestClient;
import com.api.utils.StringUtils;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserRequest extends BaseTest{
	
	@BeforeMethod
	public void getUserSetUP() {
		
		restClient= new RestClient(baseURI,prop);
				
	}
	
	@DataProvider
	public Object[][] createUserTestData() {
		return new Object[][] {
			{"meg","female","active"},
			{"na","female","inactive"},
			{"sada","male","active"}
			
		};
	}
	
	
	@Test(dataProvider="createUserTestData")
	public void createUserRequestTest(String name,String gender,String status) {
		
		User user = new User(name,StringUtils.getRandomEmail(),gender,status);
	Integer userId=	restClient.post("/public/v2/users", "json", user, true, true)
		.then()
		.log()
		.all()
		.assertThat().statusCode(HTTPStatus.CREATED_201.getCode())
		.extract().path("id");
	
	RestClient getClient = new RestClient(baseURI, prop);
	getClient.get("/public/v2/users"+"/"+userId, true, true)
	.then()
	.log()
	.all()
	.statusCode(HTTPStatus.OK_200.getCode())
	.assertThat().body("id", equalTo(userId));
		
		
	}
	
	

}
