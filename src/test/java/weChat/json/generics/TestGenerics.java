package weChat.json.generics;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import weChat.bean.BusiBean;
import weChat.domain.Customer;

public class TestGenerics {
	
	@Test
	public void testGenerics() throws JsonGenerationException, JsonMappingException, IOException{
		BusiBean<Customer>  bean = new BusiBean<>();
		bean.setCmd("12343");
		Customer customer = new Customer("林龙灯","灯");
		bean.setApi_params(customer);
		   ObjectMapper mapper = new ObjectMapper();
		   mapper.writeValue(System.out, bean);
	}
	@Test
	public void  testGenerics2() throws JsonParseException, JsonMappingException, IOException{
		String json ="{\"cmd\":\"12343\",\"api_params\":"
				+ "{\"id\":0,\"firstName\":\"林龙灯\",\"createdDate\":1428894135759,\"lastName\":\"灯\"}}";
		   ObjectMapper mapper = new ObjectMapper();
		   BusiBean<Customer> bean = mapper.readValue(json,  new TypeReference<BusiBean<Customer>>(){});
		   System.out.println(bean);
		   
		   
		
	}

}
