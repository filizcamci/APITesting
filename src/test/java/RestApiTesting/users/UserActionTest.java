package RestApiTesting.users;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserActionTest {
Faker faker=new Faker();
  
  @BeforeClass
  public void init() {
    
	  RestAssured.baseURI = "https://www.filizblog.dev.cc/wp-json";
	    basePath = "/wp/v2" ; 
  }
  
  @Test
  public void testPublicUserGetOnlyAdminProfileInfo() {
    
    given()
      .relaxedHTTPSValidation().
      //auth().preemptive().basic("admin", "admin").
    when()
      //.log().all()
      .get("/users").
    then()
      .statusCode(HttpStatus.SC_OK)
      //.statusCode(200) 
      //.contentType(ContentType.JSON)
      .header("Content-Type", "application/json; charset=UTF-8")
      .body("id", hasSize(1) )
      .body("name", hasItem("admin") )
      
    ;

  }
  
  @Test
  public void testPublicUserShouldNotBeAble_CreateNewUser() {
    
    given()
      .relaxedHTTPSValidation().
      //.auth().preemptive().basic("admin", "admin").
    when()
      .log().all()
      .body("{" +
          "  \"username\" : \"user_b\" ,\n" + 
          "  \"name\" : \"user b\" ,\n" + 
          "  \"first_name\" : \"super b\", \n" + 
          "  \"last_name\" : \"user last\" ,\n" + 
          "  \"email\" : \"s@aaa.com\" ,\n" + 
          "  \"roles\" : \"author\" ,\n" + 
          "  \"password\" : \"user\" \n" + 
          "}")
      .contentType(ContentType.JSON)
      .post("/users").
    then()
      //.statusCode(HttpStatus.SC_UNAUTHORIZED)
      .statusCode(401) 
      .contentType(ContentType.JSON)
//      .header("Content-Type", "application/json; charset=UTF-8")
      .body("code", is("rest_cannot_create_user") )
//      .body("name", hasItem("admin") )
//      
    ;

  }
  
  
  @Test
  public void testAdminUserShouldBeAble_CreateNewUser() {
    
    given()
      .relaxedHTTPSValidation()
      .auth().preemptive().basic("admin", "admin").
    when()
      .log().all()
      .body("{" +
          "  \"username\" : \"user_c\" ,\n" + 
          "  \"name\" : \"user c\" ,\n" + 
          "  \"first_name\" : \"super b\", \n" + 
          "  \"last_name\" : \"user last\" ,\n" + 
          "  \"email\" : \"s@aaa.com\" ,\n" + 
          "  \"roles\" : \"author\" ,\n" + 
          "  \"password\" : \"user\" \n" + 
          "}")
      .contentType(ContentType.JSON)
      .post("/users").
    then()
      //.statusCode(HttpStatus.SC_CREATED)
        .statusCode(201) 
      .contentType(ContentType.JSON)
//      .header("Content-Type", "application/json; charset=UTF-8")
      .body("username", is("user_c") )
//      .body("name", hasItem("admin") )
//      
    ;

  }
  
  @Test
  public void testOtherUsersShouldNotBeAble_CreateNewUser() {
    
    given()
      .relaxedHTTPSValidation()
      .auth().preemptive().basic("userb", "userb").
    when()
      .log().all()
      .body("{" +
          "  \"username\" : \"user_z\" ,\n" + 
          "  \"name\" : \"userz\" ,\n" + 
          "  \"first_name\" : \"firstz\", \n" + 
          "  \"last_name\" : \"lastz\" ,\n" + 
          "  \"email\" : \"z@z.com\" ,\n" + 
          "  \"roles\" : \"author\" ,\n" + 
          "  \"password\" : \"userz\" \n" + 
          "}")
      .contentType(ContentType.JSON)
      .post("/users").
    then()
      //.statusCode(HttpStatus.SC_CREATED)
        .statusCode(403) 
        ;
    }
  
  
  @Test
	public void adminUser_ShouldBeAbleto_UpdateExistingUser() {
	  given()
		.relaxedHTTPSValidation()
		.auth().preemptive().basic("admin", "admin")
		.contentType(ContentType.JSON)	
	.when()
		.log().all()
		.body("{\r\n" + 
				"	\"first_name\" : \"user4\", \r\n" + 
				"	\"last_name\" : \"user4\" ,\r\n" + 
				"	\"email\" : \"user4@gmail.com\" ,\r\n" + 
				"	\"roles\" : \"author\" ,\r\n" + 
				"	\"password\" : \"user4\" \r\n" + 
				"}")
			
		.pathParam("newId", 4)
		.put("/users/{newId}")
	.then()
	//	.statusCode(HttpStatus.SC_CREATED)
		.statusCode(200)
		//.header("Content-Type", "application/json; charset=UTF-8")
		.body("first_name", is("user4"))
		;

	}
	
	@Test
	public void adminUser_ShouldBeAbleto_DeleteExistingUser() {
		given()
		.relaxedHTTPSValidation()
		.auth().preemptive().basic("admin", "admin")
		.param("force", true) //queryParam("force", true)
		.param("reassign", 1)
		//.pathParam("Id", 4)
	.when()
		.log().all()
		.delete("/users/5")
		//delete("/users/{Id}")
	.then()
		.statusCode(HttpStatus.SC_OK)
		//.statusCode(200)
		.contentType(ContentType.JSON)
		.body("deleted", is(true))
		.body("previous.id", equalTo(5))
		;
	}
	
	@Test
	public void publicUser_ShouldNotBeAbleto_View_ExistingUser_otherThanAdmin() {
		 given()
	      .relaxedHTTPSValidation()
	      .auth().preemptive().basic("userf", "userf")
	      //.pathParam("Id", 3);
	    .when()
	      //.get("/users/{id}").
	    .get("/users/2").
	    then()
	      //.statusCode(HttpStatus.SC_OK)
	      .statusCode(403) 
	      //.contentType(ContentType.JSON)
	      //.header("Content-Type", "application/json; charset=UTF-8")
	      //.body("id", hasSize(1) )
	      //.body("name", hasItem("admin") )
	      .body("message", is("Sorry, you are not allowed to list users."))
	      ;
	      
	  
	}	
	
	//JSONPATH is a library to work with JSON DATA
	 
	  public int createRandomUser() {
	   Response response = given()
	  .relaxedHTTPSValidation()
	  .auth().preemptive().basic("admin", "admin").
	when()
	  .log().all()
	  .accept(ContentType.JSON)
	  .body("{" +
	      "  \"username\" : \""+faker.name().firstName()+"\" ,\n" + 
	      "  \"name\" : \"user c\" ,\n" + 
	      "  \"first_name\" : \"super b\", \n" + 
	      "  \"last_name\" : \"user last\" ,\n" + 
	      "  \"email\" : \""+faker.internet().emailAddress()+"\" ,\n" + 
	      "  \"roles\" : \"author\" ,\n" + 
	      "  \"password\" : \"user\" \n" + 
	      "}")
	  .contentType(ContentType.JSON)
	  .post("/users");
	   System.out.println(response);
	    int newID =  response.jsonPath().getInt("id");
	    System.out.println("new id is: "+newID);
	    return newID;
	 }
  
}
