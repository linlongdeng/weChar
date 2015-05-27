package weChat.web;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import weChat.core.metatype.BaseDto;
import weChat.json.PostJsonUtils;
import weChat.parameter.impl.RReqParam;
import weChat.parameter.impl.RRespParam;

public class WechatMqControllerTest {

	
	@Test
	public void testController() throws JsonGenerationException, JsonMappingException, IOException{
		String actionPath ="/RabbitmqRpc";
		RReqParam param = new RReqParam();
		param.setCmdid("WJ007");
		//param.setCompanycode("06375"); 
		param.setCompanycode("01103");
		param.setWechatpubinfoid(1);
		BaseDto dto = new BaseDto();
		dto.put("cardnum", "5000028");
		param.setParams(dto);
		Map<String, Object> result = PostJsonUtils.postObject(actionPath, param);
		System.out.println(result);
	}
	@Test
	public void testHttp() throws JsonGenerationException, JsonMappingException, IOException{
		PostJsonUtils.ip ="http://123.59.55.176:3002";
		String actionPath ="";
		RReqParam param = new RReqParam();
		param.setCmdid("WJ007");
		//param.setCompanycode("06375"); 
		param.setCompanycode("01103");
		//param.setCompanycode("01197");
		param.setWechatpubinfoid(1);
		BaseDto dto = new BaseDto();
		dto.put("cardnum", "5000028");
		param.setParams(dto);
		Map<String, Object> result = PostJsonUtils.postObject(actionPath, param);
		System.out.println(result);
	}
}
