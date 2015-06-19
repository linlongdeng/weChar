package weChat.web.km.controller;

import static weChat.utils.AppConstants.*;

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

	/**K米APP获取绑卡信息
	 * @param company
	 * @param otherParam
	 * @param wechatpubinfoid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/bindCardInfo")
	public IRespParam bindCardInfo(@ModelAttribute(COMPANY) Company company,
			@ModelAttribute(OTHER_PARAM) Dto otherParam,
			@ModelAttribute(WECHATPUBINFOID) int wechatpubinfoid)
			throws Exception {
		IRespParam resp = kmService.bindCardInfo(company, otherParam,
				wechatpubinfoid);
		return resp;

	}

	/**K米APP批量获取会员信息
	 * @param customerid
	 * @return
	 */
	@RequestMapping("/memberInfo")
	public IRespParam memberInfo(@ModelAttribute(CUSTOMERID) int customerid) {
		IRespParam resp = kmService.memberInfo(customerid);
		return resp;
	}
/**
 * K米App绑卡
 * @param wechatpubinfoid
 * @param customerid
 * @param otherParam
 * @return
 * @throws Exception 
 */
	@RequestMapping("/bindCard")
	public IRespParam bindCard(
			@ModelAttribute(WECHATPUBINFOID) int wechatpubinfoid,
			@ModelAttribute(CUSTOMERID) int customerid,
			@ModelAttribute(OTHER_PARAM) Dto otherParam) throws Exception {
		return kmService.bindCard(wechatpubinfoid, customerid, otherParam);
	}

}
