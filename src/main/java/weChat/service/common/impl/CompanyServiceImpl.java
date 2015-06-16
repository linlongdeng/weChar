package weChat.service.common.impl;

import static weChat.utils.AppUtils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import weChat.domain.primary.Company;
import weChat.repository.primary.CompanyRepository;
import weChat.service.common.AuthService;
import weChat.service.common.CompanyService;
import weChat.utils.AppConstants;
import weChat.utils.AppUtils;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyRepository  companyRepository;
	@Autowired
	private AuthService authService;
	@Override
	public void validateCompany(String companycode, String accessToken, int wechatpubinfoid,
			Model model) {
		Company company = companyRepository.findFirstByCompanyCode(companycode);
		//商家不能为空
		assertCompanyNotNull(company);
		//验证权限
		AppUtils.assertTrueAccess(authService.checkAccessToken(accessToken));
		model.addAttribute(AppConstants.COMPANY, company);
		model.addAttribute(AppConstants.WECHATPUBINFOID, wechatpubinfoid);
	}

}
