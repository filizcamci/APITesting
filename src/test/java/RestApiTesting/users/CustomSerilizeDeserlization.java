package RestApiTesting.users;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.ObjectMapper;

// Jackson --> com.fasterxml 
// GSon    ---> google 

public class CustomSerilizeDeserlization {

  
  @Test
  public void test() throws Exception{
    
    //String greeting = "{\"name\":\"Adam\" , \"message\":\"Hello\"}" ; 
    
    String greeting = "{\"name\":\"Adam\" , \"message\":\"Hello\" , \"extraMessage\":\"goodbye\"}" ; 
    
    ObjectMapper mapper = new ObjectMapper(); 
    
    Greeting g1 = mapper.readValue(greeting , Greeting.class) ; 
    System.out.println(g1);
    
    System.out.println("--------------------");
    String str = mapper.writeValueAsString(g1) ; 
    System.out.println(str);
    
    
    GOT_Character[] people = mapper.readValue(peopleJson, GOT_Character[].class) ; 
    System.out.println( Arrays.toString(people)  );
    
    
    
    Greeting g2 = new Greeting("Akbar","GoodNight") ; 
    System.out.println(  mapper.writeValueAsString(g2) );
    
    
    
    
    
  }

  
  
  String peopleJson  = " [\n" + 
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
  
  
  
}

@JsonIgnoreProperties(ignoreUnknown = true )
//@JsonIgnoreProperties("extraMessage")
class Greeting{
  
  //@JsonIgnore //it is used to ignore pojo field when converting to json object
  String name ; 
  String message ; 
  
  public Greeting() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public String toString() {
    return "Greeting [name=" + name + ", message=" + message + "]";
  }

  public Greeting(String name, String message) {
    super();
    this.name = name;
    this.message = message;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  
  
  
  
  //extra field 
  //String extraMessage ; 
  
}

