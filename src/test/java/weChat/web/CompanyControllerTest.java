package weChat.web;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import weChat.json.PostJsonUtils;
import weChat.parameter.manager.MInfoReqParam;

public class CompanyControllerTest {

	@Test
	public void testCompanyInfo() throws JsonGenerationException, JsonMappingException, IOException{
		String actionPath ="/Company/company_info";
		MInfoReqParam param = new MInfoReqParam();
		param.setCompanycode("00125");
		param.setAccess_token("51b3mal99w1tpikjzcs6b6a7ps72yj");
		param.setWechatpubinfoid(43423432);
		Map<String, Object> result = PostJsonUtils.postObject(actionPath, param);
		System.out.println(result);
	}
}
