package weChat.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.util.JSONPObject;

import weChat.core.metatype.BaseDto;
import weChat.json.PostJsonUtils;
import weChat.parameter.manage.MRequestParam;

public class TestGradecollectController {

	@Test
	public void testSyncGrade() throws JsonGenerationException, JsonMappingException, IOException{
		String actionPath ="/Membersync/GradeCollect";
		MRequestParam param = new MRequestParam();
		param.setCompanycode("00127");
		param.setCompanypsw("123");
		param.setWechatPubInfoID("43243243");
		List<BaseDto> list = new ArrayList<>();
		BaseDto map = new BaseDto();
		map.put("gradeid", 123323);
		map.put("gradecode", "4324343");
		map.put("gradename", "测试3343434234324");
		map.put("status", 0);
		list.add(map);
		param.setData(list);
		Map<String, Object> result = PostJsonUtils.postObject(actionPath, param);
		System.out.println(result);
		
	}
}
