package weChat.json.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeserializeZoo {

	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {
		String json = "{\"name\":\"London Zoo\",\"city\":\"London\","
				+ "\"animals\":[{\"@class\":\"weChat.json.jackson.Elephant\",\"name\":\"Manny\"},"
				+ "{\"@class\":\"weChat.json.jackson.Lion\",\"name\":\"Simba\"}]}";
		ObjectMapper mapper = new ObjectMapper();
		Zoo zoo = mapper.readValue(json, Zoo.class);
		
		System.out.println(zoo);
	}
}