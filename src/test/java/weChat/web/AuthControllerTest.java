package weChat.web;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import weChat.core.metatype.BaseDto;
import weChat.core.utils.HttpClientUtils;
import weChat.parameter.impl.DtoParam;
import weChat.parameter.impl.KAuthReqParam;


public class AuthControllerTest {

	private String ip = "http://192.168.82.119:8080";
	@Test
	public void testGetAccessToken() throws Exception{
		String actionPath = "/Auth/getAccessToken";
		
		KAuthReqParam param = new KAuthReqParam();
		param.setAppkey("8930ec48a3fsfd32047a9fcda127db");
		param.setAppid(90000001);
		param.setGranttype("kmapp");
		BaseDto pDto = HttpClientUtils.post(ip + actionPath, param,
				BaseDto.class);
		System.out.println("返回参数是：" + pDto );
	}
@Test
	public void testCheckToken() throws Exception{
		String actionPath = "/Auth/checkToken";
		DtoParam param = new  DtoParam();
		param.put("access_token", "f1kbmht72c3wjydtbir07cal6okm8a");
		BaseDto pDto = HttpClientUtils.post(ip + actionPath, param,
				BaseDto.class);
		System.out.println("返回参数是：" + pDto );
	}
}
