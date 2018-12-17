package auth;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class JWT_Auth {

  /// BEFORE TEST ---> CREATE NEW USER AND GET ID
  /// TEST --- > UPDATE USER INFO 
  /// TEST ----> DELETE THAT CREATED USER
  
  Faker faker = new Faker();
  int newUserID ; 
  String token;
  String bearerToken;
  
 // @BeforeClass
  public void init() {
    
    RestAssured.baseURI = "https://www.fiiz-qa.dev.cc" ; 
    RestAssured.basePath = "/wp-json/wp/v2" ; 
    RestAssured.useRelaxedHTTPSValidation();
    token=getToken();
    bearerToken="Bearer "+token;
    
  }
  
  public String getToken() {
	  RestAssured.basePath = "/wp-json/jwt-auth/v1" ;
	  Response res=
	  given()
	  .accept(ContentType.JSON)
	  .contentType(ContentType.URLENC)
	  .formParam("username", "admin")
	  .formParam("password", "admin")
	  .when()
	  //.log().all()
	  .post("/token")
	  ;
	  token=res.jsonPath().getString("token");
	  return token;
  }
  @Test
  public void test() {
	  
	  RestAssured.basePath = "/wp-json/wp/v2" ; 

    Map<String,Object> mp = new HashMap<>() ; 
    mp.put("title", faker.book().title());
    mp.put("content", "quote of the day: confidence is key in job interview");
    mp.put("status", "publish");

    given()
    //.header("Authorization","Bearer "+token)
    .header("Authorization",bearerToken)
    //.auth().preemptive().basic("admin", "admin")//not using with token
    .contentType(ContentType.JSON)
    .body(mp).

  when()
    .log().all()
    .post("/posts").
  then()
    //.statusCode(HttpStatus.SC_CREATED)
    .statusCode(201)
    .header("Content-Type" , containsString("application/json") )
    ;
    //.body("title.raw", is("abc") ) ; 
    
  }
  @Test
  public void getAllModuleTest() {
	  RestAssured.baseURI = "https://learn.cybertekschool.com/api/v1" ;
	  Response res=
	  given()
      .accept(ContentType.JSON)
      .auth().oauth2("10960~A1m8QZ0ByihvGBqRuK5fcYrbBrrMS0tjIR7cjsbyztvZp9ctfZ5qpF2Ye1032CQq").

    when()
      .log().all()
      .get("/courses/73/modules");
	  
   res.then()
      //.statusCode(HttpStatus.SC_OK)
      .statusCode(200)
      .body("$" , hasSize(9)) ; 
   System.out.println(res.jsonPath().getList("modules[0]"));
//res.jsonPath().getList("")
    
  }
  
  
  
  
}

