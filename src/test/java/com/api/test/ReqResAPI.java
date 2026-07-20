package com.api.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.restclient.RestClient;

public class ReqResAPI extends BaseTest{
	
	@BeforeMethod
	public void reqresSetup() {
		restClient = new RestClient(baseURI,prop);
	}
	
	@Test
	public void getReqResUsers_list_Test() {
		restClient.get("/api/users", true, true)
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(200);
		
		System.out.println("unable to test this api as they added authorization");
		
	}

}
