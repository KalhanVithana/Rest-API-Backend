package com.user.User.TestCase.User;

import org.testng.annotations.Test;


import com.sun.xml.xsom.impl.scd.Iterators.Map;

import org.testng.Assert;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.json.simple.JSONObject;


public class TestCase {
	
	


	
	
	@Test
	public void  Test_1() {
		
		Response response = RestAssured.get("http://localhost:8082/User/webapi/acc");
		
		System.out.println(response.asString());
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		System.out.println(response.getTime());
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		
	}
	
	@Test
	public void Test_2() {

		given().get("http://localhost:8082/User/webapi/acc/a/3").
		then().
		statusCode(200).
		body("id", equalTo(3));
		
		
	}
	@Test
	public void Test_3_Post() {
		
		
		/*HashMap<String, Object> map = new  HashMap<String, Object>();
		
		
		
		
		map.put("id", "1");
		map.put("name", "abcde");
		map.put("email", "sagak@");
		map.put("mobile", "vvvcb");
		map.put("password", "sdgxaxc");
		
		//System.out.println(map);
*/
		
		
		JSONObject request = new JSONObject();
		
		request.put("email", "acc");
		request.put("id", "0");
		request.put("mobile", "dxv");
		request.put("name", "acc");
		request.put("password", "xdvg");
		
		System.out.println(request);
		System.out.println(request.toJSONString());
		
		given()
		.contentType("application/json")
		.body(request)
			
	.when()
		.post("http://localhost:8082/User/webapi/acc/regi")
			
	.then()
		.statusCode(200)
		.log().body();

	}

	
	
	

/*	@org.junit.Test
 * 
 * 	@org.junit.Test
	public void test_UserCreate() {
		
		given()
		
		.when()
			.get("http://localhost:8082/User/webapi/acc/regi")
			
		.then()
			.statusCode(200);
	}
	
	public void test_Users() {
		
		given()
		
		.when()
			.get("http://localhost:8082/User/webapi/acc")
			
		.then()
			.statusCode(200);
	}
	
	@org.junit.Test
	public void test_User() {
		
		given()
		
		.when()
			.get("http://localhost:8082/User/webapi/acc/a/1")
			
		.then()
			.statusCode(200)
			.log().body()
			.body("id", equalTo(1));
	}
	
	
	*/
	
	
	
	
	
	

	
	
}
