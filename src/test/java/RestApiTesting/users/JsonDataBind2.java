package RestApiTesting.users;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class JsonDataBind2 {
	
Faker faker=new Faker();
	
	@BeforeClass
	  public void init() {
	    
		  RestAssured.baseURI = "https://www.filizblog.dev.cc/wp-json";
		    basePath = "/wp/v2" ; 
	  }
	@Test
	public void postWithMap() {
		
		Map<String,Object> mp= new HashMap<>();
		//mp.put("title", faker.book().title());
		mp.put("title", faker.internet().emailAddress());
		//mp.put("title", "abc");
		mp.put("content", faker.book().author());
		mp.put("status", "publish");
		
		 given()
	      .relaxedHTTPSValidation()
	      .auth().preemptive().basic("admin", "admin")
	      .contentType(ContentType.JSON)
	      .body(mp).
	    when()
	      .log().all()
	     
	      .post("/posts").
	    then()
	      //.statusCode(HttpStatus.SC_UNAUTHORIZED)
	      .statusCode(201) 
	      ;
	}
		
	@Test
	public void postWithPojo() {
		
		//PostPojo p=new PostPojo("Pojo title","Pojo content","publish");
		PostPojo p=new PostPojo(faker.book().title(),"Pojo content","publish");
		 given()
	      .relaxedHTTPSValidation()
	      .auth().preemptive().basic("admin", "admin")
	      .contentType(ContentType.JSON)
	      .body(p).
	    when()
	      .log().all()
	     
	      .post("/posts").
	    then()
	      //.statusCode(HttpStatus.SC_UNAUTHORIZED)
	      .statusCode(201) 
	      ;
	}
		
		
		
		
		
		
		
}
class PostPojo{
	
	String title;
	String content;
	String status;
	
	public PostPojo() {
	
	}

	public PostPojo(String title, String content, String status) {
		super();
		this.title = title;
		this.content = content;
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Post [title=" + title + ", content=" + content + ", status=" + status + "]";
	}
	
}
