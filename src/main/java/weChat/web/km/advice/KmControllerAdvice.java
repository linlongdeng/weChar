package weChat.web.km.advice;

import static weChat.utils.AppConstants.COMPANY;
import static weChat.utils.AppConstants.OTHER_PARAM;
import static weChat.utils.AppConstants.WECHATPUBINFOID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import weChat.core.exception.ArgumentEmptyException;
import weChat.core.utils.CommonUtils;
import weChat.core.utils.ValidationUtils;
import weChat.domain.primary.Company;
import weChat.domain.primary.Companywechatpub;
import weChat.parameter.km.KDynamicReqParam;
import weChat.repository.primary.CompanyRepository;
import weChat.repository.primary.CompanywechatpubRepository;
import weChat.repository.primary.WechatpubinfoRepository;
import weChat.service.common.AuthService;
import weChat.utils.AppUtils;
import weChat.web.km.controller.KmController;

@ControllerAdvice(assignableTypes = { KmController.class })
/**
 * KMController的通知
 * @author deng
 * @date 2015年6月16日
 * @version 1.0.0
 */
public class KmControllerAdvice {
	@Autowired
	private AuthService authService;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private WechatpubinfoRepository wechatpubinfoRepository;
	@Autowired
	private CompanywechatpubRepository companywechatpubRepository;

	@ModelAttribute
	public void populateModel(@RequestBody @Valid KDynamicReqParam param,
			HttpServletRequest request, Model model) {
		String requestURI = request.getRequestURI();
		// 验证token
		AppUtils.assertTrueAccess(authService.checkAccessToken(param
				.getAccess_token()));
		// K米APP获取绑卡信息
		if (requestURI.lastIndexOf("bindCardInfo") >= 0) {
			//数据校验，数据处理
			handleBindCardInfo(param, model);
		}

	}

	/**
	 * 处理K米APP获取绑卡信息
	 * 
	 * @param param
	 * @param model
	 */
	private void handleBindCardInfo(KDynamicReqParam param, Model model) {
		// 校验参数非空
		ValidationUtils.rejectEmpty(
				new Object[] { param.getCompanyid(), param.get("cardnum") },
				new String[] { "companyid", "cardnum" });
		Company company = companyRepository.findOne(param.getCompanyid());
		// 商家不能为空
		AppUtils.assertCompanyNotNull(company);
		model.addAttribute(COMPANY, company);
		Companywechatpub companywechatpub = companywechatpubRepository
				.findFirstByCompanyID(company.getCompanyID());
		// 商家公众号
		AppUtils.assertWechatNotNull(companywechatpub);
		model.addAttribute(WECHATPUBINFOID,
				companywechatpub.getWechatPubInfoID());
		// 其他参数
		model.addAttribute(OTHER_PARAM, param.any());
	}
}
