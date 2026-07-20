package com.api.constants;

public enum HTTPStatus {
	
	OK_200(200, "Ok"),
	CREATED_201(201, "Created"),
	BAD_REQUEST_404(404, "Bad Request"),
	NO_CONTENT_204(204, "No Content"),
	UNAUTHORIZED_401(401, "Unauthorized"),
	FoRBIDDEN_403(403, "ForBidden"),
	NOT_FOUND_404(404, "Not Found"),
	INTERNAL_SERVER_ERROR_500(500, "Internal Server Error");
	
	private final int code;
	private final String message;
	
	HTTPStatus(int code,String message){
		this.code = code;
		this.message=message;
		
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	
	

}
