package RestApiTesting.users;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

//json object --> Driver object
//Data binding : binding Json field to POJO field
//to achieve data binding we added jackson databind dependency to pom.xml
/*
 <dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.9.6</version>
		</dependency>
 */
public class JsonToJava {
	@Test
	public void getTest() {
		Response response = 
				given()
				//.relaxedHTTPSValidation()
				// .queryParam("per_page",2)
				
				.get("http://ergast.com/api/f1/drivers.json");
		JsonPath jp = response.jsonPath();
		Driver driver = jp.getObject("MRData.DriverTable.Drivers[1]", Driver.class);
		System.out.println(driver);
		
}
}
