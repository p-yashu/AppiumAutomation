import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class AddAPI{

	public static void main(String[] args) {

		//validate if ADD PLACE API is working as expected
		
		//given //log.all
		//when
		//then
		System.out.println("git demo");
		
		//adding details = POST
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response =given().log().all()
						 .queryParam("key","qaclick123")
						 .header("Content-Type", "application/json")
						 .body(payLoad.addPlace())
						 .when().post("maps/api/place/add/json")
						 .then().log().all().assertThat().statusCode(200)
						 .body("scope",equalTo("APP"))
						 .header("Server",equalTo("Apache/2.4.52 (Ubuntu)"))
						 .extract().response().asString();
		System.out.println(response);
		
		//Updateding Address with PUT
		
		JsonPath js = new JsonPath(response);
		String placeID = js.getString("place_id");
		String newAddress = "70 winter walk, USA";
		
		  					given().log().all()
		  					.queryParam("key","qaclick123")
		  					.header("Content-Type", "application/json")
		  					.body("{\r\n"
		  							+ "\"place_id\":\""+placeID+"\",\r\n"
		  							+ "\"address\":\""+newAddress+"\",\r\n"
		  							+ "\"key\":\"qaclick123\"\r\n"
		  							+ "}")
		  					.when().put("maps/api/place/update/json")
		  					.then().log().all().assertThat().statusCode(200)
		  					//.body("scope",equalTo("App"))
		  					.body("msg",equalTo("Address successfully updated"));
		  					
		  					
		 //get place with get
		  					
		  String newResponse =given().log().all()
				  			.queryParam("key", "qaclick123")
		  					.queryParam("place_id", placeID)
		  					.when().get("maps/api/place/get/json")
		  					.then().log().all().assertThat().statusCode(200)
		  					.extract().response().asString();
		  
		  JsonPath js1 = new JsonPath(newResponse);
		  String updatedAddress = js1.getString("address");
		  System.out.println(updatedAddress);
		  //Assert.assertEquals(updatedAddress, newAddress);
		  Boolean result = updatedAddress.equals(newAddress);
		  System.out.println(result);

		  //delete the addrees
		  //String deletedAddress = 
			String deletedResponse = given().log().all()
										.queryParam("key","qaclick123")
									    .body("{\n" +
									              "\"place_id\":\"" + placeID + "\"\n" +
									              "}")
										.when().delete("maps/api/place/delete/json")
										.then().log().all().assertThat().statusCode(200)
										.body("status",equalTo("OK"))
										.extract().response().asString();
				  
			JsonPath js2 = new JsonPath(deletedResponse);
			String  status = js2.getString("status");
			Assert.assertEquals(status, "OK");
			
		  					
		 
		  					
		  					
	}

}
