import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class DynamicJson extends BaseTest{

	@Test(dataProvider="BooksData")
	public void addBook(HashMap<String,String> input)
	{
		//body=instead of payload.addingBook
		//body=json file= covert to byte->byte to string
		
		RestAssured.baseURI="http://216.10.245.166";
		String response =given().log().all()
							.header("Content-Type","application/json")
							.body(payLoad.addingBook(input.get("name"),input.get("isbn"),input.get("aisle"),input.get("author")))
							.when().post("/Library/Addbook.php")
							.then().log().all().assertThat().statusCode(200)
							.extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String id = js.get("ID");
		System.out.println(id);
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData()throws IOException
	{
		{
			List<HashMap<String, String>> data =getJsonData(System.getProperty("user.dir")+"\\src\\booksData.json");
					
			return new Object[][] {
									{data.get(0)},
									{data.get(1)}
								   };
		}
		
	}
	
}
