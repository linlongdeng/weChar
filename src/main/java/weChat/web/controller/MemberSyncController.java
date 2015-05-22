package weChat.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import weChat.core.metatype.BaseDto;
import weChat.core.metatype.Dto;
import weChat.domain.primary.Company;
import weChat.parameter.impl.MReqParam;
import weChat.parameter.impl.MRespParam;
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
	public MRespParam memberLevel(@ModelAttribute Company company,
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
	public MRespParam memberInfo(@ModelAttribute Company company,
			@ModelAttribute(AppConstants.WECHATPUBINFOID) int wechatpubinfoid,
			@ModelAttribute(AppConstants.DATA) List<Dto> data) {
		return memberSyncService.memberInfo(company, wechatpubinfoid, data);
	}

}
