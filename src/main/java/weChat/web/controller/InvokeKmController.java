package weChat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import weChat.parameter.IRespParam;
import weChat.parameter.impl.DynamicRespParam;
import weChat.service.InvokeKmService;

@RestController
public class InvokeKmController {

	@Autowired
	private InvokeKmService invokeKmService;

	@RequestMapping("/getKmAuth")
	public IRespParam getKmAuth() throws Exception {
		 String token = invokeKmService.getKmAccessToken();
		 DynamicRespParam resp = new DynamicRespParam();
		 resp.set("access_token", token);
		return resp;

	}
	@RequestMapping("/saveAllCompanyFromKm")
	public IRespParam saveAllCompanyFromKm() throws Exception{
		IRespParam resp = invokeKmService.saveAllCompanyFromKm();
		return resp;
	}
}
