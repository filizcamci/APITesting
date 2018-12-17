package RestApiTesting.users;

import java.io.IOException;
import java.util.Arrays;

import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
///Task:  Create a class GOT_Character ---> fields  : --> id , male , house ,  name  
public class CharacterTest {
	 //@Test
//	  public void test() throws Exception{
//	    
//	    //String greeting = "{\"name\":\"Adam\" , \"message\":\"Hello\"}" ; 
//	    
//	    String greeting = "{\"name\":\"Adam\" , \"message\":\"Hello\" , \"extraMessage\":\"goodbye\"}" ; 
//	    
//	    ObjectMapper mapper = new ObjectMapper(); 
//	    
//	    Greeting g1 = mapper.readValue(greeting , Greeting.class) ; 
//	    System.out.println(g1);
//	    
//	    System.out.println("--------------------");
//	    String str = mapper.writeValueAsString(g1) ; 
//	    System.out.println(str);
//	    
//	    
//	    
//	  }
	 @Test
	 public void testCharacter() throws Exception {

	
	  
	  String str  = " [\n" + 
	      "    {\n" + 
	      "        \"_id\": \"56ffc5be043244081938576d\",\n" + 
	      "        \"male\": true,\n" + 
	      "        \"house\": \"House Hightower\",\n" + 
	      "        \"slug\": \"Abelar_Hightower\",\n" + 
	      "        \"name\": \"Abelar Hightower\",\n" + 
	      "        \"__v\": 0,\n" + 
	      "        \"pageRank\": 2.5,\n" + 
	      "        \"books\": [\n" + 
	      "            \"The Hedge Knight\"\n" + 
	      "        ],\n" + 
	      "        \"updatedAt\": \"2016-04-02T13:14:38.834Z\",\n" + 
	      "        \"createdAt\": \"2016-04-02T13:14:38.834Z\",\n" + 
	      "        \"titles\": [\n" + 
	      "            \"Ser\"\n" + 
	      "        ]\n" + 
	      "    },\n" + 
	      "    {\n" + 
	      "        \"_id\": \"56ffc5be043244081938576e\",\n" + 
	      "        \"male\": true,\n" + 
	      "        \"house\": \"House Frey\",\n" + 
	      "        \"slug\": \"Addam_Frey\",\n" + 
	      "        \"name\": \"Addam Frey\",\n" + 
	      "        \"__v\": 0,\n" + 
	      "        \"pageRank\": 4.5,\n" + 
	      "        \"books\": [\n" + 
	      "            \"The Mystery Knight\"\n" + 
	      "        ],\n" + 
	      "        \"updatedAt\": \"2016-04-02T13:14:38.875Z\",\n" + 
	      "        \"createdAt\": \"2016-04-02T13:14:38.875Z\",\n" + 
	      "        \"titles\": [\n" + 
	      "            \"Ser\"\n" + 
	      "        ]\n" + 
	      "    },\n" + 
	      "    {\n" + 
	      "        \"_id\": \"56ffc5be043244081938576f\",\n" + 
	      "        \"male\": true,\n" + 
	      "        \"slug\": \"Addam\",\n" + 
	      "        \"name\": \"Addam\",\n" + 
	      "        \"__v\": 0,\n" + 
	      "        \"pageRank\": 1.5,\n" + 
	      "        \"books\": [\n" + 
	      "            \"The Mystery Knight\"\n" + 
	      "        ],\n" + 
	      "        \"updatedAt\": \"2016-04-02T13:14:38.877Z\",\n" + 
	      "        \"createdAt\": \"2016-04-02T13:14:38.877Z\",\n" + 
	      "        \"titles\": [\n" + 
	      "            \"Ser\"\n" + 
	      "        ]\n" + 
	      "    } ] " ; 
	  
	  ObjectMapper mapper = new ObjectMapper(); 
	    
	   GOT_Character[] ppl  = mapper.readValue(str , GOT_Character[].class) ; 
	    System.out.println(Arrays.toString(ppl));
	    System.out.println(ppl.length);
	    
	    System.out.println("--------------------");
	  //  String str = mapper.writeValueAsString(g1) ; 
	  //  System.out.println(str);
	  
	}
}
@JsonIgnoreProperties(ignoreUnknown = true )//to ignore extra field in json object we use this annotation
//@JsonIgnoreProperties("extraMessage")
class GOT_Character{
	@JsonProperty("_id")//it is _id in json and id in pojo, this is the solution to get _id value from json
	String id;
	
	boolean male;
	String house;
	String name;
	
	@Override
	public String toString() {
		return "Character[ name:"+ name +", id:"+id+", house:"+house+"]";
	}
	public GOT_Character() {
	
	}
	public GOT_Character(String id, boolean male, String house, String name) {
		super();
		this.id = id;
		this.male = male;
		this.house = house;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isMale() {
		return male;
	}
	public void setMale(boolean male) {
		this.male = male;
	}
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

	

