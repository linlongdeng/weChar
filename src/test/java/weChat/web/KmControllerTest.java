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
	
	@Test
	public void testMemberConsumeInfo() throws Exception{
		String actionPath = "/Km/memberConsumeInfo";
		KDynamicReqParam param = new KDynamicReqParam();
		param.setCompanyid(1197);
		param.setAccess_token("q7372jjwctzx4ha1rgv5jucspitotv");
		param.set("kmid","111111");
		param.set("begintime", "2015-01-01 00:00:00");
		param.set("endtime", "2015-06-23 23:59:59");
		BaseDto pDto = HttpClientUtils.post(ip + actionPath, param,
				BaseDto.class);
		System.out.println(pDto);
	}
	@Test
	public void testUpdateMemberInfo() throws Exception{
		String actionPath = "/Km/updateMemberInfo";
		KDynamicReqParam param = new KDynamicReqParam();
		param.setCompanyid(1197);
		param.setAccess_token("q7372jjwctzx4ha1rgv5jucspitotv");
		param.set("kmid","000000023");
		param.set("membername","林龙灯3");
		param.set("sex", "男");
		param.set("birthday", "1988-10-21");
		param.set("mobile", "18960863890");
		BaseDto pDto = HttpClientUtils.post(ip + actionPath, param,
				BaseDto.class);
		System.out.println(pDto);
	}

}
