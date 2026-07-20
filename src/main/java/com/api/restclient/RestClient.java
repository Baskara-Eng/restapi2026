package com.api.restclient;

import java.util.Map;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	
	//private static final String BASE_URI="https://gorest.co.in/";
	//private static final String BASE_TOKEN= "e5bdeeaf245ab5554713f0a7d4c602d44feb6b20a37586cf40b141a8ae7e74ad";
	private String baseURI;
	private Properties prop;
	private static RequestSpecBuilder specBuilder;
	private boolean isAuthorizationHeader=false;
	
	
//	static {
//		specBuilder = new RequestSpecBuilder();
//		
//	}
	
	public RestClient(String baseURI, Properties prop) {
		specBuilder = new RequestSpecBuilder();
		this.baseURI=baseURI;
		this.prop=prop;
		
		
	}
	public void addAuthorization() {
		if(!isAuthorizationHeader) {
		specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("token"));
		isAuthorizationHeader=true;
		}
	}
	
	public void setContentType(String contentType) {
		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;
		case "html":
			specBuilder.setContentType(ContentType.HTML);
			break;

		default:
			System.out.println("Please provide appropriate contentType");
		}
	}
	
	private RequestSpecification createRequest(boolean includeAuth) {
		
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorization();
		}
		
		return specBuilder.build();
		
	}
	
	private RequestSpecification createRequest(Map<String,String>headerMap,boolean includeAuth) {
		
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorization();
		}
	
		if(headerMap!=null) {
			specBuilder.addHeaders(headerMap);
		}
		return	specBuilder.build();
		
	}
	
	private RequestSpecification createRequest(Map<String,String>headerMap,Map<String,String>queryMap,boolean includeAuth) {
		
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorization();
		}
		
		if(headerMap!=null) {
			specBuilder.addHeaders(headerMap);
		}
		if(queryMap!=null) {
			specBuilder.addQueryParams(queryMap);
		}
		
		return specBuilder.build();
	}
	
	public RequestSpecification createRequest(Map<String,String>headerMap,String contentType,Object reqBody,boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorization();
		}
		
		if(headerMap!=null) {
			specBuilder.addHeaders(headerMap);
		}
	
		setContentType(contentType);
	
		
		if(reqBody!=null) {
			specBuilder.setBody(reqBody);
		}
		
		return specBuilder.build();
		
	}
	
	private RequestSpecification createRequest(String contentType, Object reqBody,boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		
		if(includeAuth) {
			addAuthorization();
		}
		setContentType(contentType);
		if(reqBody!=null) {
			specBuilder.setBody(reqBody);
		}
		
		return specBuilder.build();
		
	}
	
	//http methods
	
	public Response get(String serviceURL,boolean includeAuth,boolean log) {
		if(log) {
		return	RestAssured.given(createRequest(includeAuth)).log().all()
			.when()
			.get(serviceURL);
		}
				
		return	RestAssured.given(createRequest(includeAuth))
			.when()
			.get(serviceURL);
		
	}
	
	public Response get(String serviceURL,Map<String,String>headerMap,boolean includeAuth,boolean log) {
		if(log) {
		return	RestAssured.given(createRequest(headerMap,includeAuth)).log().all()
			.when()
			.get(serviceURL);
		}
				
		return	RestAssured.given(createRequest(headerMap,includeAuth))
			.when()
			.get(serviceURL);
		
	}

	public Response get(String serviceURL,Map<String,String>headerMap,Map<String,String>queryMap,boolean includeAuth,boolean log) {
		if(log) {
		return	RestAssured.given(createRequest(headerMap, queryMap,includeAuth)).log().all()
			.when()
			.get(serviceURL);
		}
				
		return	RestAssured.given(createRequest(headerMap, queryMap,includeAuth))
			.when()
			.get(serviceURL);
		
	}
	
	public Response post(String serviceURL,String contentType,Object reqBody,boolean includeAuth,boolean log) {
		
		if(log) {
			return RestAssured.given(createRequest(contentType, reqBody,includeAuth)).log().all().when().post(serviceURL);
		}
		return RestAssured.given(createRequest(contentType, reqBody,includeAuth)).when().post(serviceURL);
		
	}
	
	public Response post(String serviceURL,String contentType,Map<String,String>headermap,Object reqBody,boolean includeAuth,boolean log) {
		
		if(log) {
			return RestAssured.given(createRequest(headermap, contentType, reqBody,includeAuth)).log().all().when().post(serviceURL);
		}
		return RestAssured.given(createRequest(headermap, contentType, reqBody,includeAuth)).when().post(serviceURL);
		
	}
	
public Response put(String serviceURL,String contentType,Object reqBody,boolean includeAuth,boolean log) {
		
		if(log) {
			return RestAssured.given(createRequest(contentType, reqBody,includeAuth)).log().all().when().put(serviceURL);
		}
		return RestAssured.given(createRequest(contentType, reqBody,includeAuth)).when().put(serviceURL);
		
	}

public Response delete(String serviceURL,boolean includeAuth,boolean log) {
	
	if(log) {
		return RestAssured.given(createRequest(includeAuth)).log().all().when().delete(serviceURL);
	}
	return RestAssured.given(createRequest(includeAuth)).when().delete(serviceURL);
	
}
	
	
}
