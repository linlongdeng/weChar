package weChat.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import weChat.bean.BusiBean;
import weChat.domain.Customer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestPostJson {

	@Test
	public void testPostJson() throws JsonGenerationException,
			JsonMappingException, IOException {
		Map<String, Object> map = new HashMap<>();
		map.put("id", 100000);
		map.put("firstName", "deng");
		map.put("createdDate", new Date());
		ObjectMapper mapper = new ObjectMapper();
		StringWriter stringWriter = new StringWriter();
		mapper.writeValue(stringWriter, map);
		String params = stringWriter.toString();
		String actionPath = "/customer/getCustomer";
		PostJson.post(actionPath, stringWriter.toString());
	}

	@Test
	public void testPostJson2() throws JsonGenerationException,
			JsonMappingException, IOException {
		BusiBean<Customer> bean = new BusiBean<>();
		bean.setCmd("12343");
		Customer customer = new Customer("林龙灯", "灯");
		bean.setApi_params(customer);
		ObjectMapper mapper = new ObjectMapper();
		StringWriter stringWriter = new StringWriter();
		mapper.writeValue(stringWriter, bean);
		String actionPath = "/customer/testGenerics";
		String result = PostJson.post(actionPath, stringWriter.toString());
		System.out.println(result);
		

	}

}
