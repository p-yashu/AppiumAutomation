import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BasicAuthAPI {
		@Test
		public void JiraAPICreateIssue()
		{			
			RestAssured.baseURI= "https://gangayashaswini.atlassian.net";
			Response res = given().header("Content-Type", "application/json")
							  .header("Authorization",
									  "Basic Z2FuZ2EueWFzaGFzd2luaUBnbWFpbC5jb206QVRBVFQzeEZmR0YwWDZCYzhXaXV5V0lHaVZoSUhwZ1VCa29MOUE3eUQ2dDBWT01uQl8zNlFaY3NGajZFNVY"
									  + "5VE16VmxCNmppeUZqYzJEcGVReWhZTUtfQmZrd0o0NVliOGMtWW5mN0JjNWoxUTZNaC04dWw0d1d1eVZ1UEl6aFU2amRfbkZHLUh1Z3M2SEhGcE9taFlBdFVZMExFeHljanBDV0xsOThDMFNzVms0UUlsc1dQcVRZPUMzQjdDNzM2")
							  .body("{"+
									  "\"fields\": {"+
									  "\"project\":{"+
									  "\"key\": \"SCRUM\""+
									  "},"+
									  "\"summary\": \"Issue 5 for adding comment\","+
									  "\"issuetype\": {"+
									  "\"name\": \"Bug\""+
									  "}"+
									  "}}")
							  .when()
							  .post("/rest/api/2/issue").then().statusCode(201).extract().response();
			
			
			String responseString = res.asString();
			JsonPath js = new JsonPath(responseString);
			String id=js.get("id");
			System.out.println(id);
			
			}
}
