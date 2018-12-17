package RestApiTesting;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
//import static org.testng.AssertJUnit.assertTrue;
import static org.testng.Assert.assertEquals;
//import static org.testng.AssertJUnit.assertTrue;
//import static io.restassured.RestAssured.basePath;
//import static io.restassured.RestAssured.baseURI;
//import static io.restassured.RestAssured.given;
//import static io.restassured.RestAssured.when;
//import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertTrue;

//import org.testng.annotations.BeforeClass;
// io.restassured.RestAssured.*
//io.restassured.matcher.RestAssuredMatchers.*;
//org.hamcrest.Matchers.*
//import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RestTest {

	
	@BeforeClass
	public void setUp() {
		baseURI="https://www.filizblog.dev.cc/wp-json";
		basePath="/wp/v2";
	}
	
	//scenario:
		//given rest endpoint http://73.166.37.2:1000/ords/hr/employees/100
		//when I send a http get request to the server
		//then I should get 200 ok result as status code
	@Test
	public void firstTest() {
		when()
		.get("http://73.166.37.2:1000/ords/hr/employees/100")
		.then()
		.statusCode(200);
	}
	
	@Test
	public void deneme1() {
		when()
		.get("http://73.166.37.2:1000/ords/hr/jobs")
		.then()
		.statusCode(200);
	}
	
	@Test
	public void secondTest() {
		given()
		.relaxedHTTPSValidation()//to turn off ssl check and bypass it
		.when()
		.get("https://www.filizblog.dev.cc/wp-json/wp/v2/posts/")
		.then()
		.statusCode(200);
	}
	
	@Test
	public void idTest() {
		given()
		.relaxedHTTPSValidation()
		.when()
		.get("https://www.filizblog.dev.cc/wp-json/wp/v2/posts/12")
		.then()
		//.log().all()
		.statusCode(200)
		//.and()
		.body("id", equalTo(12))
		.body("title.rendered", equalTo("post through postman"))
		;
	}
	@Test
	public void idTest2() {
		//int id=0;
		given()
		.relaxedHTTPSValidation()
		.when()
		//.log().all()
		.get("https://www.filizblog.dev.cc/wp-json/wp/v2/posts/{id}",12)
		//.get("/posts/{id}",12)
		.then()
		.log().all()//logs everything
		.statusCode(200)
		//.and()			//these 2 are for readability 
		//.assertThat()
		.body("id", equalTo(12))
		.body("title.rendered", equalTo("post through postman"));
	}
	@Test
	public void baseURITest() {
		//RestAssured.baseURI="https://www.filizblog.dev.cc/wp-json";
		//baseURI="https://www.filizblog.dev.cc/wp-json";
		//RestAssured.basePath="/wp/v2";
		//basePath="/wp/v2";
		given()
		.relaxedHTTPSValidation()
		.when()
		//.log().all()
		.get("/posts")
		.then()
		//.log().all()
		.statusCode(200);
	}
	@Test
	public void addPostTest() {
		given()
		.relaxedHTTPSValidation() //it means trust this website used with https/ssl 
		.when()
		.post("https://www.filizblog.dev.cc/wp-json/wp/v2/posts/3")
		//..post("/posts/3")
		.then()
		.statusCode(200);
	}
	@Test
	public void thirdTest() {
		given()
		.relaxedHTTPSValidation()
		.when()
		.get("http://73.166.37.2:1000/ords/hr/jobs")
		.then()
		.statusCode(200)
		;
	}
	
}
