package RestApiTesting.users;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class JavaToJson {
	 @Test
	  public void databindTest() throws Exception {
//		 Response response=
//				    given()
//				      .relaxedHTTPSValidation()
//				      //.queryParam("per_page",2)
//				      .get("http://ergast.com/api/f1/drivers.json");
//		  JsonPath jp= response.jsonPath();
//		jp.getList("MRData.DriverTable.Drivers[0]");
	    String jsonString = "{\"driverId\": \"abate\",\n" + 
	    		"                   \"url\": \"http://en.wikipedia.org/wiki/Carlo_Mario_Abate\",\r\n" + 
	    		"                   \"givenName\": \"Carlo\",\r\n" + 
	    		"                   \"familyName\": \"Abate\",\r\n" + 
	    		"                   \"dateOfBirth\": \"1932-07-10\",\r\n" + 
	    		"                   \"nationality\": \"Italian\"}" ; 
	    ObjectMapper om  = new ObjectMapper() ; 
	    Driver obj = om.readValue( jsonString , Driver.class ) ; 
	    
	    System.out.println(jsonString);
	    System.out.println(obj);
	    
	    String convertedStr = om.writeValueAsString(obj) ; 
	    System.out.println( convertedStr );
	    

	  }
	 @Test
	 public void databindTestWithCollection() throws Exception {
	   
	   String jsonStringArr = 
	         "[ {\"name\":\"Adam\", \"age\":10} , {\"name\":\"john\", \"age\":12} , {\"name\":\"yuAN\", \"age\":25} ] " ;
	   
	   ObjectMapper om  = new ObjectMapper() ; 
	   Person[] arr = om.readValue(jsonStringArr, Person[].class) ; 
	   
	    System.out.println(  Arrays.toString( arr)   );
	   
	   
	 }
}
