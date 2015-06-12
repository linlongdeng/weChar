package weChat.web.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import weChat.core.metatype.BaseDto;
import weChat.parameter.IRespParam;
import weChat.parameter.impl.DtoParam;
import weChat.parameter.impl.DynamicRespParam;
import weChat.parameter.impl.KAuthReqParam;
import weChat.service.AuthService;
import weChat.utils.RespUtils;

@RestController
@RequestMapping("/Auth")
public class AuthController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AuthService authService;

	/**
	 * K米APP获取授权码
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/getAccessToken")
	public IRespParam getAccessToken(@RequestBody @Valid KAuthReqParam param) {
		return authService.getAccessToken(param);
	}

	/**
	 * 校验token是否成功
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/checkToken")
	public IRespParam checkToken(@RequestBody @Valid DtoParam param) {
		String access_token = param.getAsString("access_token");
		logger.debug("开始进入校验token, {}", access_token);
		boolean checkResult = authService.checkAccessToken(access_token);
		DynamicRespParam resp = new DynamicRespParam();
		resp.set("checkResult", checkResult);
		return resp;

	}

}
