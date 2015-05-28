package weChat.km;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import weChat.core.metatype.BaseDto;
import weChat.core.metatype.Dto;
import weChat.core.utils.HttpClientUtils;
import weChat.json.PostJsonUtils;

public class KmInvokeTest {

	final String ip = "http://192.168.84.176:8103";

	@Test
	public void testAccess_token() throws JsonGenerationException,
			JsonMappingException, IOException {
		String actionPath = "/Auth/access_token";
		Dto dto = new BaseDto();
		dto.put("granttype", "clientcredentials");
		dto.put("appid", "11");
		dto.put("appkey", "8930ec48a3fed32047a9fcda127db378");
		dto.put("scope", "");
		Map<String, Object> map = PostJsonUtils.postObject(actionPath, dto);
		System.out.println(map);
	}

	@Test
	public void Company_lists() throws JsonGenerationException,
			JsonMappingException, IOException {
		PostJsonUtils.ip = ip;
		String actionPath = "/Company/lists";
		Dto dto = new BaseDto();
		dto.put("access_token", "efa810814017980d0c1fb2f968423152");
		Map<String, Object> map = PostJsonUtils.postObject(actionPath, dto);
	}

	@Test
	public void testPostCompany_lists() throws Exception {
		long startTime = System.currentTimeMillis();
		String actionPath = "/Company/lists";
		String url = ip + actionPath;
		Dto param = new BaseDto();
		param.put("access_token", "efa810814017980d0c1fb2f968423152");
		BaseDto result = HttpClientUtils.<BaseDto> post(url, param, BaseDto.class);
		List list = result.getAsList("res");
		System.out.println("list 大小是" + list.size());
		long endTime = System.currentTimeMillis();
		System.out.println("花费的时间是" + (endTime - startTime) / 1000 + "s");
	}

}
