package RestApiTesting.users;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class GameOfThrone {
	/*
	   * use this end point -- GET https://api.got.show/api/characters/
	   * get only the characters belong to house stark  tips -- use findAll method 
	   * and get the count of it 
	   * and print out as String 
	   * store all first names in a list
	   * */
	
	@Test
	public void testJson() {
		 Response response  = 
				    
			      given()
			        .relaxedHTTPSValidation().
			      //  accept(ContentType.JSON).
	      get("https://api.got.show/api/characters/");
		 String responseString=response.asString();
		 JsonPath jp=response.jsonPath() ; 
		 JsonPath jp2=JsonPath.from(responseString);
		 jp2.get("house[0]");
		 //System.out.println(jp.getString("house"));
		 List<Object> houseList = jp.getList("findAll{it.house=='House Stark'}.house");
		 System.out.println("houseList: "+houseList);
		 List<String> allnames = jp.getList("name");
		 List<String> starknames = jp.getList("findAll{it.house=='House Stark'}.name");
		 System.out.println("names: "+starknames);
		 System.out.println(starknames.size());
		 
		 //this is java way:
		 List<String> lst=jp.getList("house");
		 int c=0;
		 for(String h:lst) {
			 if( h!=null && h.equalsIgnoreCase("house stark")) {
				 //System.out.println(h);
				 c++;
			 }
		 }
		 System.out.println(c);
		 //System.out.println(lst);
		 
		
		 
	}
}

