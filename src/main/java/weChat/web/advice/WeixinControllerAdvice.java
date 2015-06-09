package weChat.web.advice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import weChat.domain.primary.Wechatpubinfo;
import weChat.parameter.impl.MInfoReqParam;
import weChat.parameter.impl.WReqParam;
import weChat.repository.primary.WechatpubinfoRepository;
import weChat.utils.AppConstants;
import weChat.utils.AppUtils;
import weChat.web.controller.WeixinInfoController;

@ControllerAdvice(assignableTypes = { WeixinInfoController.class })
public class WeixinControllerAdvice {
	private WechatpubinfoRepository wechatpubinfoRepository;

	@ModelAttribute
	public void populateModel(@RequestBody @Valid WReqParam param,
			HttpServletRequest request, Model model) {
		Integer wechatpubinfoid = param.getWechatpubinfoid();
		Wechatpubinfo wechatpubinfo = wechatpubinfoRepository
				.findOne(wechatpubinfoid);
		//判断微信公众号是否存在
		AppUtils.assertWechatpubinfoNotNull(wechatpubinfo);
		model.addAttribute(AppConstants.WECHATPUBINFOID, wechatpubinfo);
	}
}
