package com.api.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.constants.HTTPStatus;
import com.api.restclient.RestClient;

public class GetRequest extends BaseTest{
	
	
	@BeforeMethod
	public void getRequestSetup() {
		restClient= new RestClient(baseURI,prop);
	}
	
	
	@Test
	public void get_all_users_Test() {
		
		restClient.get("public/v2/users",true,true)
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(HTTPStatus.OK_200.getCode());
		
	}
	
	
	
	

}
