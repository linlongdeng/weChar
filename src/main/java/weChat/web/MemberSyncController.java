package weChat.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import weChat.parameter.manage.MRequestParam;
import weChat.parameter.manage.MResponseParam;
import weChat.service.MemberSyncService;

/**
 * @author deng
 * @date 2015年4月23日
 * @version 1.0.0
 */
@RestController
@RequestMapping("/Membersync")
public class MemberSyncController{

	@Autowired
	private MemberSyncService memberSyncService;

	/**
	 * 会员等级同步
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/member_level")
	public MResponseParam memberLevel(@Valid @RequestBody MRequestParam param) {
		return memberSyncService.memberLevel(param);

	}
	/**
	 * 会员信息同步
	 * @param param
	 * @return
	 */
	@RequestMapping("/member_info")
	public MResponseParam  memberInfo(@Valid @RequestBody MRequestParam param){
		return memberSyncService.memberInfo(param);
	}



}
