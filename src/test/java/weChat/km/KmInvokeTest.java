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
import weChat.parameter.impl.DynamicRespParam;

public class KmInvokeTest {

	final String ip = "http://127.0.0.1:8080/weChat";

	@Test
	public void testAccess_token() throws JsonGenerationException,
			JsonMappingException, IOException {
		PostJsonUtils.ip = ip;
		String actionPath = "/getKmAuth";
		Dto dto = new BaseDto();
		Map<String, Object> map = PostJsonUtils.postObject(actionPath, dto);
		System.out.println(map);
	}
	@Test
	public void testGetKmAuth() throws Exception{
		String ip ="http://127.0.0.1:8080/weChat";
		String actionPath="/getKmAuth";
		Dto dto = new BaseDto();
		DynamicRespParam resp = HttpClientUtils.post(ip + actionPath, dto, DynamicRespParam.class);
		System.out.println("返回结果是" + resp);
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
	@Test
	public void testSaveAllCompanyFromKm() throws Exception{
		long startTime = System.currentTimeMillis();
		String actionPath = "/saveAllCompanyFromKm";
		String url = ip + actionPath;
		Dto param = new BaseDto();
		Map result = HttpClientUtils.post(url, param, Map.class);
		long endTime = System.currentTimeMillis();
		System.out.println("花费的时间是" + (endTime - startTime) / 1000 + "s");
		System.out.println(result);
	}

}