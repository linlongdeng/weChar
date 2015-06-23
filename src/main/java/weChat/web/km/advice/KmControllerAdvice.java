package weChat.web.km.advice;

import static weChat.utils.AppConstants.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import weChat.core.exception.ArgumentEmptyException;
import weChat.core.metatype.Dto;
import weChat.core.utils.CommonUtils;
import weChat.core.utils.ValidationUtils;
import weChat.domain.primary.Company;
import weChat.domain.primary.Companywechatpub;
import weChat.parameter.km.KDynamicReqParam;
import weChat.repository.primary.CompanyRepository;
import weChat.repository.primary.CompanywechatpubRepository;
import weChat.repository.primary.WechatpubinfoRepository;
import weChat.service.common.AuthService;
import weChat.utils.AppUtils;
import weChat.web.km.controller.KmController;

@ControllerAdvice(assignableTypes = { KmController.class })
/**
 * KMController的通知
 * @author deng
 * @date 2015年6月16日
 * @version 1.0.0
 */
public class KmControllerAdvice {
	@Autowired
	private AuthService authService;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private WechatpubinfoRepository wechatpubinfoRepository;
	@Autowired
	private CompanywechatpubRepository companywechatpubRepository;

	@ModelAttribute
	public void populateModel(@RequestBody @Valid KDynamicReqParam param,
			HttpServletRequest request, Model model) {
		String requestURI = request.getRequestURI();
		// 验证token
		AppUtils.assertTrueAccess(authService.checkAccessToken(param
				.getAccess_token()));
		// K米APP获取绑卡信息
		if (requestURI.lastIndexOf("bindCardInfo") >= 0) {
			// 数据校验，数据处理
			handleBindCardInfo(param, model);
		}
		// 处理.K米APP批量获取会员信息
		else if (requestURI.lastIndexOf("memberInfo") >= 0) {
			handleMemberInfo(param, model);
		}
		//处理K米APP绑卡
		else if(requestURI.lastIndexOf("bindCard") >= 0){
			handleBindCard(param, model);
		}
		
		// 根据电子会员卡号获取会员信息
		else if (requestURI.lastIndexOf("memberInfoByKmID") >= 0) {
			handlememberInfoByKmID(param, model);
		}

		//获取参数
		else if (requestURI.lastIndexOf("getParamer")>=0){
			handleGetParamers(param,model);
		}
		//更新参数
		else if (requestURI.lastIndexOf("updateParamer")>=0){
			handleUpdateParamer(param,model);
		}
		//K米APP获取会员消费记录
		else if(requestURI.lastIndexOf("memberConsumeInfo") >= 0){
			handleMemberConsumeInfo(param,model);
		}
		//K米APP完善会员资料
		else if(requestURI.lastIndexOf("updateMemberInfo") >= 0){
			handleUpdateMemberInfo(param,model);
		}

	}

	/**
	 * 处理K米APP获取绑卡信息
	 * 
	 * @param param
	 * @param model
	 */
	private void handleBindCardInfo(KDynamicReqParam param, Model model) {
		// 校验参数非空
		ValidationUtils.rejectEmpty(
				new Object[] { param.getCompanyid(), param.get("cardnum") },
				new String[] { "companyid", "cardnum" });
		// 获取商家信息
		getCompany(param.getCompanyid(), model);
		// 获取商家公众号
		getWechatPubInfoID(param.getCompanyid(), model);
		// 其他参数
		getOtherParam(param.any(), model);
	}

	/**
	 * 处理.K米APP批量获取会员信息
	 * 
	 * @param param
	 * @param model
	 */
	private void handleMemberInfo(KDynamicReqParam param, Model model) {
		// 校验参数非空
		ValidationUtils.rejectEmpty(new Object[] { param.get("customerid") },
				new String[] { "customerid" });
		//获取K米APP会员ID
		getCustomerid(param.any(), model);
	}

	/**
	 * 处理K米APP绑卡，校验参数，注入商家信息，微信公众号，其他参数
	 * 
	 * @param param
	 * @param model
	 */
	private void handleBindCard(KDynamicReqParam param, Model model) {
		//非空校验
		ValidationUtils.rejectEmpty(
				new Object[] { param.getCompanyid(), param.get("customerid"),
						param.get("cardnum"), param.get("moblie"),
						 }, new String[] { "companyid",
						"customerid", "cardnum","moblie" });
		
		// 获取商家信息
		getCompany(param.getCompanyid(), model);
		// 获取商家公众号
		getWechatPubInfoID(param.getCompanyid(), model);
		// 其他参数
		getOtherParam(param.any(), model);
		//获取K米APP会员ID
		getCustomerid(param.any(), model);
	}
	
	private void handleUpdateParamer(KDynamicReqParam param, Model model) {
		// 校验商家信息参数
		validationCompanyInfo(param,model);
		// 其他参数信息校验
		ValidationUtils.rejectEmpty(new Object[]{param.get("data")},new String[]{"data"});
		// 其他参数
		model.addAttribute(OTHER_PARAM, param.any());
	}


	/**获取参数值
	 * @param param
	 * @param model
	 */
	private void handleGetParamers(KDynamicReqParam param, Model model) {
		// 校验商家信息参数
		validationCompanyInfo(param,model);
		// 其他参数信息校验
		ValidationUtils.rejectEmpty(new Object[]{param.get("data")},new String[]{"data"});
		// 其他参数
		model.addAttribute(OTHER_PARAM, param.any());
	}
	/**
	 * 根据电子会员卡id获取会员信息
	 * 
	 * @param param
	 * @param model
	 */
	private void handlememberInfoByKmID(KDynamicReqParam param, Model model) {
		ValidationUtils.rejectEmpty(new Object[] { param.get("kmid") },
				new String[] { "kmid" });
		model.addAttribute(KMID, param.get("kmid"));
	}
	
	
	/**
	 * K米APP获取会员消费记录
	 * @param param
	 * @param model
	 */
	private void handleMemberConsumeInfo(KDynamicReqParam param, Model model){
		// 校验参数非空
				ValidationUtils.rejectEmpty(
						new Object[] { param.getCompanyid(), param.get("kmid"), param.get("begintime"), param.get("endtime") },
						new String[] { "companyid", "kmid","begintime","endtime" });
				// 获取商家信息
				getCompany(param.getCompanyid(), model);
				// 获取商家公众号
				getWechatPubInfoID(param.getCompanyid(), model);
				// 其他参数
				getOtherParam(param.any(), model);
		
	}
	
	/**K米APP完善会员资料
	 * @param param
	 * @param model
	 */
	private void handleUpdateMemberInfo(KDynamicReqParam param, Model model){
		ValidationUtils.rejectEmpty(
				new Object[] { param.getCompanyid(), param.get("kmid"), param.get("membername"), 
						param.get("sex"),param.get("mobile"),param.get("birthday") },
				new String[] { "companyid", "kmid","membername","sex","mobile","birthday" });
		// 获取商家信息
		getCompany(param.getCompanyid(), model);
		// 获取商家公众号
		getWechatPubInfoID(param.getCompanyid(), model);
		// 其他参数
		getOtherParam(param.any(), model);
	}
	
	/**商家信息校验及参数填充
	 * @param param
	 * @param model
	 */
	private void validationCompanyInfo(KDynamicReqParam param, Model model){
		ValidationUtils.rejectEmpty(
				new Object[]{param.getCompanyid()},new String[] { "companyid"});
	
		// 获取商家信息
		getCompany(param.getCompanyid(), model);
		// 获取商家公众号
		getWechatPubInfoID(param.getCompanyid(), model);
	}
	/**
	 * 获取商户
	 * 
	 * @param companyid
	 */
	private void getCompany(int companyid, Model model) {
		Company company = companyRepository.findOne(companyid);
		// 商家不能为空
		AppUtils.assertCompanyNotNull(company);
		model.addAttribute(COMPANY, company);
	}

	/**
	 * 获取微信公众号ID
	 * 
	 * @param companyid
	 * @param model
	 */
	private void getWechatPubInfoID(int companyid, Model model) {
		Companywechatpub companywechatpub = companywechatpubRepository
				.findFirstByCompanyID(companyid);
		// 商家公众号
		AppUtils.assertWechatNotNull(companywechatpub);
		model.addAttribute(WECHATPUBINFOID,
				companywechatpub.getWechatPubInfoID());
	}

	/**
	 * 获取其他参数
	 * 
	 * @param param
	 * @param model
	 */
	private void getOtherParam(Dto param, Model model) {
		// 其他参数
		model.addAttribute(OTHER_PARAM, param);
	}
	/**
	 * 获取K米APP会员ID
	 * @param param
	 * @param model
	 */
	private void getCustomerid(Dto param, Model model){
		model.addAttribute(CUSTOMERID, param.getAsInteger("customerid"));
	}
}
