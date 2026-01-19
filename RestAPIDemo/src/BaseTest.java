import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.path.json.JsonPath;

public class BaseTest {

	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {

				String jsonContent = FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);

				ObjectMapper mapper = new ObjectMapper();
				List<HashMap<String, String>> data = mapper.readValue(jsonContent,
						new TypeReference<List<HashMap<String, String>>>() {
						});

				return data;

			}
	
	public static JsonPath rawToJson(String response)
	{
		JsonPath js1 =new JsonPath(response);
		return js1;
	}
	
}
