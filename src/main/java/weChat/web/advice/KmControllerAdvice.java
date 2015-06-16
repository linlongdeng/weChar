package weChat.web.advice;

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

import weChat.core.exception.ArgumentErrorException;
import weChat.core.utils.CommonUtils;
import weChat.domain.primary.Company;
import weChat.domain.primary.Companywechatpub;
import weChat.parameter.impl.KDynamicReqParam;
import weChat.repository.primary.CompanyRepository;
import weChat.repository.primary.CompanywechatpubRepository;
import weChat.repository.primary.WechatpubinfoRepository;
import weChat.service.AuthService;
import weChat.utils.AppUtils;
import weChat.web.controller.KmController;



@ControllerAdvice(assignableTypes={KmController.class})
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
		if (requestURI.lastIndexOf("bindCardInfo") >= 0) {
			if (CommonUtils.isEmpty(param.getCompanyid(), param.get("cardnum"))) {
				throw new ArgumentErrorException();
			}
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
			model.addAttribute(OTHER_PARAM, param.any());
		}
	
	}
}
