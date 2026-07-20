package com.api.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.api.configManager.ConfigurationManager;
import com.api.restclient.RestClient;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	
	protected RestClient restClient;
	protected ConfigurationManager configManager;
	protected Properties prop;
	protected String baseURI;
	
	@Parameters({"baseURI"})
	@BeforeTest
	public void setup(String baseURI) {
		RestAssured.filters(new AllureRestAssured());
		configManager = new ConfigurationManager();
		prop= new Properties();
		prop=configManager.init_prop();
		this.baseURI=baseURI;
		//restClient = new RestClient(baseURI,prop);
		
		
		
	}

}
