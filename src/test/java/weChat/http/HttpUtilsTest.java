package weChat.http;

import org.junit.Test;

import weChat.core.metatype.BaseDto;
import weChat.core.utils.HttpClientUtils;
import weChat.parameter.impl.MInfoReqParam;

public class HttpUtilsTest {

	String ip ="http://192.168.82.119:8080/weChat";
	
	@Test
	public void testPost() throws Exception{
		String actionPath="/Rabbitmq/info";
		String url = ip + actionPath;
		MInfoReqParam param = new MInfoReqParam();
		param.setCompanycode("00127");
		param.setCompanypsw("25d55ad283aa400af464c76d713c07ad");
		param.setWechatpubinfoid(43423432);
		BaseDto result = HttpClientUtils.<BaseDto>post(url, param, BaseDto.class);
		System.out.println(result);
		
	}
}
