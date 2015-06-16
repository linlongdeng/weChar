package weChat.web.advice;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import weChat.domain.primary.Company;
import weChat.parameter.impl.MReqParam;
import weChat.repository.primary.CompanyRepository;
import weChat.service.CompanyService;
import weChat.utils.AppConstants;
import weChat.web.controller.MemberSyncController;

@ControllerAdvice(assignableTypes={MemberSyncController.class})
public class MemberSyncControllerAdvice {

	@Autowired
	private CompanyService companyService;
	@ModelAttribute
	public void populateModel(@RequestBody @Valid MReqParam param, Model model){
		String companycode = param.getCompanycode();
		String accessToken = param.getAccess_token();
		int wechatpubinfoid = param.getWechatpubinfoid();
		companyService.validateCompany(companycode, accessToken, wechatpubinfoid, model);
		model.addAttribute(AppConstants.DATA, param.getData());
		
		
	}
}
