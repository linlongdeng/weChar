package weChat.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import weChat.core.metatype.Dto;
import weChat.domain.primary.Company;
import weChat.parameter.IRespParam;
import weChat.service.MemberSyncService;
import weChat.utils.AppConstants;

/**
 * @author deng
 * @date 2015年4月23日
 * @version 1.0.0
 */
@RestController
@RequestMapping("/Membersync")
public class MemberSyncController {

	@Autowired
	private MemberSyncService memberSyncService;

	/**
	 * 会员等级同步
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/member_level")
	public IRespParam memberLevel(@ModelAttribute Company company,
			@ModelAttribute(AppConstants.WECHATPUBINFOID) int wechatpubinfoid,
			@ModelAttribute(AppConstants.DATA) List<Dto> data) {
		return memberSyncService.memberLevel(company, wechatpubinfoid, data);

	}

	/**
	 * 会员信息同步
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/member_info")
	public IRespParam memberInfo(@ModelAttribute Company company,
			@ModelAttribute(AppConstants.WECHATPUBINFOID) int wechatpubinfoid,
			@ModelAttribute(AppConstants.DATA) List<Dto> data) {
		return memberSyncService.memberInfo(company, wechatpubinfoid, data);
	}

}
