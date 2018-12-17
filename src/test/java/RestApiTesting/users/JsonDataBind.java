package RestApiTesting.users;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.PagesPerMinute;

import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonDataBind {

	@Test
	public void databindTest() throws Exception {

		String jsonString = "{\"name\" : \"Adam\",\"age\" : 21}";
		ObjectMapper om = new ObjectMapper();//from jackson library
		Person obj = om.readValue(jsonString, Person.class);//readValue method converts json to java object

		System.out.println(jsonString);
		System.out.println(obj);

		String convertedStr = om.writeValueAsString(obj);//writeValue method converts java object to json
		System.out.println(convertedStr);

		// response.jsonpath() = JsonPath.from(response.asString())

		Person p1 = JsonPath.from(jsonString).getObject("", Person.class);
		System.out.println(p1);

	}

	@Test
	public void databindTestWithCollection() throws Exception {

		String jsonStringArr = "[ {\"name\":\"Adam\", \"age\":10} , {\"name\":\"john\", \"age\":12} , {\"name\":\"yuAN\", \"age\":25} ] ";

		//Person[] pplArr = JsonPath.from(jsonStringArr).getObject("$", Person[].class);
		Person[] pplArr = JsonPath.from(jsonStringArr).getObject("", Person[].class);
		System.out.println("JSONPATH ARRAY " + Arrays.toString(pplArr));

		List<Person> pplList = JsonPath.from(jsonStringArr).getList("", Person.class);
		System.out.println("JSONPATH LIST " + pplList);

		ObjectMapper om = new ObjectMapper();
		Person[] arr = om.readValue(jsonStringArr, Person[].class);

		System.out.println("Array ---> " + Arrays.toString(arr));

		String jsonArray = om.writeValueAsString(arr);
		System.out.println("JSON Array ---> " + jsonArray);

		List<Person> ppl = Arrays.asList(new Person("aaa", 11), new Person("bbb", 12), new Person("ccc", 13));
		String jsonPPL = om.writeValueAsString(ppl);
		System.out.println("JSON List ---> " + jsonPPL);

		// converting to an Arraylist instead of Array
		// we need to use a typeReference object --> Type reference is a abstract class
		// with no abstract method thats why you see body{}
		List<Person> lst = om.readValue(jsonStringArr, new TypeReference<List<Person>>() {});

		System.out.println("List ---> " + lst);

	}

	// TASK ---
	/*
	 * { "driverId": "abate", "url":
	 * "http://en.wikipedia.org/wiki/Carlo_Mario_Abate", "givenName": "Carlo",
	 * "familyName": "Abate", "dateOfBirth": "1932-07-10", "nationality": "Italian"
	 * }
	 * 
	 * Convert This Json object to Java object using object Mapper from Jackson
	 * Databind Library
	 * 
	 */
	@Test
	public void taskTest() throws Exception {
		Response res = given()
				// .when()
				.get("http://ergast.com/api/f1/drivers.json");
		JsonPath jp = res.jsonPath();
		String obj = jp.getString("MRData.DriverTable.Drivers[0]");
		Map<String, String> dr1 = jp.getMap("MRData.DriverTable.Drivers[0]");
		String values = dr1.toString();
		//System.out.println(obj);
		String str = "{ \"driverId\": \"abate\", \"url\": \"http://en.wikipedia.org/wiki/Carlo_Mario_Abate\", \"givenName\": \"Carlo\"," + 
				"\"familyName\": \"Abate\", \"dateOfBirth\": \"1932-07-10\", \"nationality\": \"Italian\"" + " }";
		ObjectMapper om = new ObjectMapper();
		Driver dr = om.readValue(str, Driver.class);
		System.out.println(dr);

	}
	@Test
	public void databindWithCollection() {
		
	}

	@Test
	public void TestWithOnlyJsonPath() {

		Response res = given()
				// .when()
				.get("http://ergast.com/api/f1/drivers.json");
		JsonPath jp = res.jsonPath();
		List<Object> pplList = jp.getList("MRData.DriverTable.Drivers.nationality");
		Object js = jp.getString("MRData.DriverTable.Drivers.givenName");
		Map<Object, Object> m = jp.getMap("MRData.DriverTable.Drivers[3]");
		System.out.println("------------");
		System.out.println(pplList);
		System.out.println(js);
		System.out.println(m);
		System.out.println("------------");

	}

}

class Person {

	String name;
	int age;

	public Person() {
	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}
