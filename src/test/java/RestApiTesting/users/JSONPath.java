package RestApiTesting.users;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class JSONPath {

  Faker faker = new Faker();
  int newUserID ; 
  
  //@BeforeClass
  public void init() {
    
	  RestAssured.baseURI = "https://www.filizblog.dev.cc/wp-json";
	    basePath = "/wp/v2" ; 
    
  }
  @Test
  public void testJSONPath() {
    

	  RestAssured.baseURI = "https://www.filizblog.dev.cc/wp-json";
	  basePath = "/wp/v2" ; 
    Response response  = 
    
      given()
        .relaxedHTTPSValidation().
      //.auth().preemptive().basic("admin", "admin").
      when()
        .log().all()
        .get("/users/{id}",1) ;
        //.get("http://ergast.com/api/f1/drivers.json/");
    
    
    System.out.println( response.asString()  );
    response.prettyPrint();
    
    JsonPath jsonPath = response.jsonPath() ; 
    //String name=jsonPath.setRoot("MRData.DriverTable").get("Drivers[1].givenName");
    //String name=jsonPath.getString("MRData.DriverTable.Drivers[1].givenName");
    //System.out.println(name);
    //assertThat(name, equalToIgnoringCase("george"));
    //assertThat(name,equalTo("George"));
    
    System.out.println( "id:"+jsonPath.getInt("id") );
   // System.out.println("title:"+jsonPath.getString("title.rendered"));
    System.out.println("slug:"+jsonPath.getString("slug"));
    System.out.println("self:"+jsonPath.getString("_links.self"));
    System.out.println("self:"+jsonPath.getString("_links.self[0]"));
    System.out.println("href:"+jsonPath.getString("_links.self[0].href"));
    // title , slug , self
    
  ;
    XmlPath x;
    //xmlpath is another library to work with xml response
  }
  
 
  
}
