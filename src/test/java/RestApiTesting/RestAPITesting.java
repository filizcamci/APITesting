package RestApiTesting;
import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestAPITesting {

  @BeforeClass
  public void setUp() {
    
    RestAssured.baseURI = "https://www.filizblog.dev.cc/wp-json";
    basePath = "/wp/v2" ; 
    
  }
  @Test
  public void getAllPosts() {
    
    given()
      .relaxedHTTPSValidation()
    .when()
      .log().all()
      //.queryParam("per_page",1)
     // pathparam("whatever",1)
      //get("/posts/{whatever}")
      //get("/posts/{whatever}",1) //line 29+30=31
      
      .get("/posts")
      //.body().prettyPrint()
    .then()
      .log().all() 
      .assertThat()
      .statusCode(200)
      .body("id", hasItem(12))
//    .body("sticky", is(false))
      //.body("title.rendered", is("Hello world!"))
     // .body()
      //.prettyprint()
      //with index
      //  .body("id[0]", equalTo(31))
      //.body("title.rendered[0]", is("my awsome"))
      ;
    
    // TASK . CHECK YOUR RESPONSE ID AND TITLE IS AS EXPECTED IN YOUR APP
      
    
  }
  
  @Test
  public void getOnePost() {
    
    given()
      .relaxedHTTPSValidation()
    .when()
      //.log().all()
      //.queryParam("per_page",1)
     // pathparam("whatever",1)
      //get("/posts/{whatever}")
      //get("/posts/{whatever}",1) //line 62+63=64
      //.get("/posts/17") //this is what we get eventually
      .get("/posts/{id}",21)
      //.body().prettyPrint()
    .then()
      //.log().all() 
      .assertThat()
      .statusCode(200)
      .body("id", is(21))
     ;
//    .body("sticky", is(false))
      //.body("title.rendered", is("Hello world!"))
     // .body()
      //.prettyprint()
      //with index
      //  .body("id[0]", equalTo(31))
      //.body("title.rendered[0]", is("my awsome"))
  }
  @Test
  public void printBody() {
	  given()
      .relaxedHTTPSValidation()
    .when()
      .get("/posts")
     .body().prettyPrint()
     ;
  }
  @Test
  public void postTest() {
	  
	  given()
	  .relaxedHTTPSValidation()
	  .auth().preemptive().basic("admin", "admin")
	  .contentType(ContentType.JSON)
	  
	  .when()
	  .body("{\n"+
		    "\"title\":\"post through eclipse2\" ,"+
		   " \"content\": \"horray\","+
		    "\"status\" : \"publish\"}")
	  .post("/posts")
	  .then()
	  .log().all()
	  .statusCode(201)
	  .body("title.rendered", is("post through eclipse2"))
	 ;
	  
  }
  
  @Test
  public void putTest() {
	  
	  given()
	  .relaxedHTTPSValidation()
	  .auth().preemptive().basic("admin", "admin")
	  .contentType(ContentType.JSON)
	  
	  .when()
	  .log().all()
	  .body("{\n"+
		    "\"title\":\"post through eclipse3\" ,"+
		   " \"content\": \"some change...\","+
		    "\"status\" : \"publish\"}")
	  .pathParam("id", 23)
	  .put("/posts/{id}")
	  //.put("/posts/23")
	  .then()
	  .log().all()
	  .statusCode(200)
	  .body("title.rendered", is("post through eclipse3"))
	  .body("content.raw",is("some change..."))
	  
	 ;
	  
  }
  
  @Test
  public void deleteTest() {
	  
	  given()
	  .relaxedHTTPSValidation()
	  .auth().preemptive().basic("admin", "admin")
	  
	  .when()
	  .log().all()
	  .pathParam("id", 22)
	  .queryParam("force", true) //this is specific to wordpress
	  .delete("/posts/{id}")
	  //.put("/posts/22")
	  .then()
	  .log().all()
	  .statusCode(200)
	  .body("deleted", is(true))
  ;
  }
  
}
