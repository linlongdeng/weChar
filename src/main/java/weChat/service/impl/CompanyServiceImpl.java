package weChat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import weChat.domain.primary.Company;
import weChat.repository.primary.CompanyRepository;
import weChat.service.CompanyService;
import weChat.utils.AppConstants;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyRepository  companyRepository;
	@Override
	public void validateCompany(String companycode, String companypsw, int wechatpubinfoid,
			Model model) {
		Company company = companyRepository.findFirstByCompanyCode(companycode);
		Assert.notNull(company, "商家不存在");
		Assert.isTrue(company.getCompanyPsw().equals(companypsw),"商家密码错误");
		model.addAttribute(AppConstants.COMPANY, company);
		model.addAttribute(AppConstants.WECHATPUBINFOID, wechatpubinfoid);
	}

}
