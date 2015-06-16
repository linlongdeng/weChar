package weChat.web;

import org.junit.Test;

import weChat.core.metatype.BaseDto;
import weChat.core.utils.HttpClientUtils;
import weChat.parameter.km.KDynamicReqParam;

public class KmControllerTest {
	private String ip = "http://192.168.82.119:8080";
	@Test
	public void testBindCardInfo() throws Exception{
		String actionPath = "/Km/bindCardInfo";
		KDynamicReqParam param = new KDynamicReqParam();
		param.setCompanyid(1197);
		param.setAccess_token("rhu451eqeo05nkmtlhuya97hznr47q");
		//param.set("cardnum", "5000028");
		BaseDto pDto = HttpClientUtils.post(ip + actionPath, param,
				BaseDto.class);
		System.out.println(pDto);
	}
}
