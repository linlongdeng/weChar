package weChat.web;

import org.junit.Test;

import weChat.core.metatype.BaseDto;
import weChat.core.metatype.Dto;
import weChat.core.utils.HttpClientUtils;
import weChat.parameter.km.KDynamicReqParam;

public class KmControllerTest {
	private String ip = "http://192.168.82.119:8080";
	@Test
	public void testBindCardInfo() throws Exception{
		String actionPath = "/Km/bindCardInfo";
		KDynamicReqParam param = new KDynamicReqParam();
		param.setCompanyid(1197);
		param.setAccess_token("4gns6xtu01g9w62zu1rsi9hzqgear3");
		param.set("cardnum", "5000028");
		BaseDto pDto = HttpClientUtils.post(ip + actionPath, param,
				BaseDto.class);
		System.out.println(pDto);
	}
	@Test
	public void testMemberInfo() throws Exception{
		Dto  param = new BaseDto();
		String actionPath = "/Km/memberInfo";
		param.put("access_token", "4gns6xtu01g9w62zu1rsi9hzqgear3");
		param.put("customerid", 1293137);
		BaseDto pDto = HttpClientUtils.post(ip + actionPath, param,
				BaseDto.class);
		System.out.println(pDto);
		
	}
	
	
	@Test
	public void testBindCard() throws Exception{
		String actionPath = "/Km/bindCard";
		KDynamicReqParam param = new KDynamicReqParam();
		param.setCompanyid(1197);
		param.setAccess_token("4gns6xtu01g9w62zu1rsi9hzqgear3");
		param.set("moblie","13500001111");
		param.set("cardnum", "5000028");
		param.set("customerid",1197);
		BaseDto pDto = HttpClientUtils.post(ip + actionPath, param,
				BaseDto.class);
		System.out.println(pDto);
	}

}
