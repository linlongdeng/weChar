package weChat.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import weChat.core.metatype.BaseDto;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class TestObject {

	@Test
	public void testObjectMapper() throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		BaseDto dto = new BaseDto();
		dto.put("aaa", "1234");
		dto.put("aBc", "456");
		StringWriter stringWriter = new StringWriter();
		objectMapper.writeValue(stringWriter, dto);
		String value = stringWriter.toString();
		System.out.println(value);
		Persion persion = new Persion();
		persion.setAaa("1111");
		persion.setABc("2222");;
		stringWriter =  new StringWriter();
		objectMapper.writeValue(stringWriter, persion);
		value = stringWriter.toString();
		System.out.println(value);
	}
	
	public static class Persion{
		private String ABc;
		
		private String aaa;

		public String getABc() {
			return ABc;
		}

		public void setABc(String aBc) {
			ABc = aBc;
		}

		public String getAaa() {
			return aaa;
		}

		public void setAaa(String aaa) {
			this.aaa = aaa;
		}
	}
}


