package RestApiTesting.users;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonPath_PRACTICE2 {

	Faker faker = new Faker();
	int newUserID;

	 @BeforeClass
	public void init() {

		RestAssured.baseURI = "https://www.filizblog.dev.cc/wp-json";
		basePath = "/wp/v2";
		RestAssured.useRelaxedHTTPSValidation();

	}

	@Test
	public void simpleGetRequest() {
		//RestAssured.baseURI = "https://www.filizblog.dev.cc/wp-json";
		//basePath = "/wp/v2";
		Response response = 
				given().
				//relaxedHTTPSValidation().
				when().log().all()
				// .queryParam("per_page",2)
				.get("/posts")
		// .then()
		// .log().all()
		// .assertThat()
		// .statusCode(200)
		;
		JsonPath jsonpath = response.jsonPath();
		System.out.println(jsonpath.getInt("author[0]"));
	}
//USE JSON PATH TO FIND OUT FIRST AUTHOR 
	@Test
	public void firstAuthor() {
		Response response = 
				given().
				get("/posts");
		JsonPath jp=response.jsonPath();
		System.out.println(jp.getInt("[0].author"));
		System.out.println(jp.getInt("author[0]"));
	}
//USE JSON PATH TO FIND OUT ALL OF THE AUTHOR 
	@Test
	public void allAuthors() {
		Response response = 
				given().
				get("/posts");
		JsonPath jp=response.jsonPath();
		System.out.println(jp.getList("author"));
		List<Integer> list = jp.getList("author", Integer.class);
		System.out.println(list);
	}
//USE JSON PATH TO FIND OUT version-history count 
	@Test
	public void versionHistoryCount() {
		Response response = 
				given().
				get("/posts");
		JsonPath jp=response.jsonPath();
		System.out.println(jp.getInt("[0]._links.version-history[0].count"));
		System.out.println(jp.getList("_links.version-history.count"));
		List<Integer> lstCount=jp.getList("_links.version-history[0].count", Integer.class);
		System.out.println(lstCount);
	}

	@Test
	public void getGivenNames() {
		Response response = 
				given()
				//.relaxedHTTPSValidation()
				// .queryParam("per_page",2)
				
				.get("http://ergast.com/api/f1/drivers.json");
		JsonPath jp = response.jsonPath();
		List<String> names = jp.getList("MRData.DriverTable.Drivers.givenName");
		List<String> givennames = jp.getList("MRData.DriverTable.Drivers.givenName",String.class);
		for (String name : names) {
			if (name.length() == 3)
				System.out.print(name + ", ");
		}
		// System.out.println(names);

		// System.out.println(jp.get("MRData.DriverTable.Drivers[0]") );

		Map driver1 = jp.getMap("MRData.DriverTable.Drivers[0]");
		System.out.println(driver1);
		System.out.println(driver1.keySet());

		Map<String, String> map2 = jp.getMap("MRData.DriverTable.Drivers[0]", String.class, String.class);
		System.out.println(map2.values());

		//System.out.println(jp.getString("MRData.DriverTable.Drivers[1].givenName"));

		// JSONPATH That rest assured use is the GPath from groovy
		// Predicate
		//it should be a collection or array before findAll method
		List<Object> givenNames=jp.getList("MRData.DriverTable.Drivers.findAll{it.givenName=='George'}");
		List<Object> lst2=jp.getList("MRData.DriverTable.Drivers.findAll{whatever->whatever.givenName=='George'}");
		System.out.println( givenNames);
		// System.out.println(
		List<Object> lst3=jp.get("MRData.DriverTable.Drivers.findAll{ it.givenName=='George' && it.nationality=='American' }");
		List<Object> lst4=jp.get("MRData.DriverTable.Drivers.findAll{driver -> driver.givenName=='George'}");//predicate way
		
		// find out all the driver that has 3 letters given name it.givenName.length()
		List<Object> lst5=jp.getList("MRData.DriverTable.Drivers.findAll{driver -> driver.givenName.length() == 3}");//predicate way
				
		System.out.println(jp.getList("MRData.DriverTable.Drivers.findAll{it.givenName.length() == 3}"));//groovy way
		/*
		 * Predicate<String> predicate = name -> name.equalsIgnoreCase("George");
		 * lstGivenName.forEach(name -> { if (predicate.test(name)) {
		 * System.out.println("Georges: "+ name); } });
		 */
		List<Object> lst6=jp.get("MRData.DriverTable.Drivers.findAll{driver -> driver.givenName.length() == 3}.givenName");//we can get/print only names or whatever we want
		System.out.println(lst6);
		Driver dr = jp.getObject("MRData.DriverTable.Drivers[1]", Driver.class);
		
	}
	@Test
	public void getTest() {
		Response response = 
				given()
				//.relaxedHTTPSValidation()
				// .queryParam("per_page",2)
				
				.get("http://ergast.com/api/f1/drivers.json");
		JsonPath jp = response.jsonPath();
		Object a = jp.get();
		System.out.println(a.getClass());
	}

}
