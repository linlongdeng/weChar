package weChat.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.util.JSONPObject;

import weChat.core.metatype.BaseDto;
import weChat.json.PostJsonUtils;
import weChat.parameter.impl.MReqParam;

public class TestGradecollectController {

	@Test
	public void testSyncGrade() throws JsonGenerationException, JsonMappingException, IOException{
		String actionPath ="/Membersync/member_level";
		MReqParam param = new MReqParam();
		param.setCompanycode("00127");
		param.setCompanypsw("25d55ad283aa400af464c76d713c07ad");
		param.setWechatpubinfoid(43243243);
		List<BaseDto> list = new ArrayList<>();
		BaseDto map = new BaseDto();
		map.put("gradeid", 123323);
		map.put("gradecode", "4324343");
		map.put("gradename", "林龙灯123");
		map.put("status", 0);
		list.add(map);
		param.setData(list);
		Map<String, Object> result = PostJsonUtils.postObject(actionPath, param);
		System.out.println(result);
		
	}
	@Test
	public void testMemberInfo() throws JsonGenerationException, JsonMappingException, IOException{
		String actionPath ="/Membersync/member_info";
		MReqParam param = new MReqParam();
		param.setCompanycode("00127");
		param.setCompanypsw("25d55ad283aa400af464c76d713c07ad");
		param.setWechatpubinfoid(43243243);
		List<BaseDto> list = new ArrayList<>();
		BaseDto map = new BaseDto();
		map.put("kmid", "1234567");
		map.put("gradeid", 0	);
		map.put("cardnum", "456");
		map.put("memberid", "123456790");
		map.put("membername", "张三");
		map.put("birthday", "1977-01-01");
		map.put("sex", "男");
		map.put("papertype", "身份证");
		map.put("papernumber", "350101011001010");
		map.put("createcardtime", "2010-01-01 11:00:00");
		map.put("memberpsw", "");
		map.put("status", "启用");
		map.put("mobile", "13500001111");
		map.put("useLimitdate", "2014-01-01");
		map.put("accountbalance", 200.23);
		map.put("integralbalance", 2512);
		map.put("consumetotal", 541.2);
		map.put("consumetimes", 909);
		map.put("lastconsumetime", "2010-01-01 11:00:00");
		map.put("accountcash", 20);
		map.put("accountpresent", 343);
		map.put("accountbalance", 33.44);
		map.put("integralbalance", 33);
		map.put("consumetotal", 33);
		list.add(map);
	
		param.setData(list);
		Map<String, Object> result = PostJsonUtils.postObject(actionPath, param);
		System.out.println(result);
	}
}
