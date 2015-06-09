package weChat.web.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import weChat.domain.primary.Wechatpubinfo;
import weChat.parameter.IRespParam;
import weChat.parameter.impl.WReqParam;
import static weChat.utils.AppConstants.*;

@RestController
@RequestMapping("/WeixinInfo")
public class WeixinInfoController {

	public IRespParam getAccessToken(
			@ModelAttribute(WECHATPUBINFOID) Wechatpubinfo wechatpubinfo) {
		return null;

	}
}
