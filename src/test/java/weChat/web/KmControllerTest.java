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
		param.put("access_token", "kde8e636fegbnxlus6y0yoch3ft5ke");
		param.put("customerid", 1293137);
		BaseDto pDto = HttpClientUtils.post(ip + actionPath, param,
				BaseDto.class);
		System.out.println(pDto);
		
	}
	
	
	@Test
	public void testMemberInfo2() throws Exception{
		 ip = "http://192.168.74.73:8080";
		Dto  param = new BaseDto();
		String actionPath = "/Km/memberInfoByKmID";
		param.put("access_token", "tujeg8j9jnc0hzleddb6pij1y0j23y");
		param.put("kmid", "000000022");
		BaseDto pDto = HttpClientUtils.postProxy(ip + actionPath, param,
				BaseDto.class);
		System.out.println(pDto);
		
	}
	@Test
	public void test() throws Exception{
		String actionPath = "/Km/test";
		KDynamicReqParam param = new KDynamicReqParam();
		param.setCompanyid(1197);
		param.setAccess_token("hftrvo9l2el3pkik0t1cy7gbrt3nqw");
		BaseDto pDto = HttpClientUtils.post(ip + actionPath, param,
				BaseDto.class);
		System.out.println(pDto);
		
		
	}
}
