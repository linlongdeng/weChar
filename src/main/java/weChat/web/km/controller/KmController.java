package weChat.web.km.controller;

import static weChat.utils.AppConstants.COMPANY;
import static weChat.utils.AppConstants.CUSTOMERID;
import static weChat.utils.AppConstants.OTHER_PARAM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import weChat.core.metatype.Dto;
import weChat.domain.primary.Company;
import weChat.parameter.IRespParam;
import weChat.service.km.KmService;
import weChat.utils.AppConstants;

@RestController
@RequestMapping("/Km")
public class KmController {
	@Autowired
	private KmService kmService;

	@RequestMapping("/bindCardInfo")
	public IRespParam bindCardInfo(@ModelAttribute(COMPANY) Company company,
			@ModelAttribute(OTHER_PARAM) Dto otherParam,
			@ModelAttribute(AppConstants.WECHATPUBINFOID) int wechatpubinfoid)
			throws Exception {
		IRespParam resp = kmService.bindCardInfo(company, otherParam,
				wechatpubinfoid);
		return resp;

	}

	@RequestMapping("/memberInfo")
	public IRespParam memberInfo(@ModelAttribute(CUSTOMERID) int customerid) {
		IRespParam resp = kmService.memberInfo(customerid);
		return resp;
	}

}
