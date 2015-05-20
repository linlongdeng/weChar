package weChat.web.advice;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import weChat.domain.primary.Company;
import weChat.parameter.manage.MRequestParam;
import weChat.repository.primary.CompanyRepository;
import weChat.web.controller.MemberSyncController;

@ControllerAdvice(assignableTypes={MemberSyncController.class})
public class MemberSyncControllerAdvice {
	@Autowired
	CompanyRepository  companyRepository;
	@ModelAttribute
	public void populateModel(@RequestBody @Valid MRequestParam param, Model model){
		String companycode = param.getCompanycode();
		Company company = companyRepository.findFirstByCompanyCode(companycode);
		String psw = param.getCompanypsw();
		Assert.notNull(company, "商家不存在");
		Assert.isTrue(company.getCompanyPsw().equals(psw),"商家密码错误");
		model.addAttribute("company", company);
		int wechatpubinfoid = param.getWechatpubinfoid();
		model.addAttribute("wechatpubinfoid", wechatpubinfoid);
		model.addAttribute("data", param.getData());
		
		
	}
}
