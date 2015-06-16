package weChat.web;

import org.junit.Test;

import weChat.core.metatype.BaseDto;
import weChat.core.utils.HttpClientUtils;
import weChat.parameter.km.KSmsReqParam;

public class InvokeKmControllerTest {
	private String ip = "http://192.168.82.119:8080";
	
	@Test
	public void testGetAccessToken() throws Exception{
		String actionPath = "/InvokeKm/sendsms";
		KSmsReqParam param = new KSmsReqParam();
		param.setPhoneno("18960863890");
		param.setValidcode("123456");
		BaseDto pDto = HttpClientUtils.post(ip + actionPath, param,
				BaseDto.class);
		System.out.println("返回参数是：" + pDto );
		
	}
	@Test
	public void test(){
		String a = "测试";
		String b = null;
		System.out.println(null + a);
	}
}
