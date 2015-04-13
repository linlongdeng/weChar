package weChat.json.jackson;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 
public class SerializeZoo {
    public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
         Zoo zoo = new Zoo("London Zoo", "London");
         Lion lion = new Lion("Simba");
         Elephant elephant = new Elephant("Manny");
         zoo.addAnimal(elephant).add(lion);
         ObjectMapper mapper = new ObjectMapper();
        // StringWriter sw = new StringWriter();
         mapper.writeValue(System.out, zoo);
    }
}