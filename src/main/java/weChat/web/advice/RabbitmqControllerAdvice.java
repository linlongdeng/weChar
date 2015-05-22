package weChat.web.advice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import weChat.parameter.impl.MInfoReqParam;
import weChat.service.CompanyService;
import weChat.web.controller.RabbitmqController;

@ControllerAdvice(assignableTypes = { RabbitmqController.class })
public class RabbitmqControllerAdvice {
	@Autowired
	private CompanyService companyService;
	@ModelAttribute
	public void populateModel(@RequestBody @Valid MInfoReqParam param,
			HttpServletRequest request, Model model) {
		String companycode = param.getCompanycode();
		String companypsw = param.getCompanypsw();
		int wechatpubinfoid = param.getWechatpubinfoid();
		companyService.validateCompany(companycode, companypsw, wechatpubinfoid, model);
	}

}
