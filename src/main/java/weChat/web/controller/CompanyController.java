package weChat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import weChat.core.utils.TypeCaseHelper;
import weChat.domain.primary.Company;
import weChat.domain.primary.Parameter;
import weChat.parameter.IRespParam;
import weChat.parameter.impl.DynamicRespParam;
import weChat.repository.primary.ParameterRepository;
import weChat.utils.AppConstants;
import weChat.utils.RespUtils;

@RestController
@RequestMapping("/Company")
public class CompanyController {
	

	@Autowired
	private ParameterRepository parameterRepository;
	/**
	 * 获取场所服务是否开启
	 * @param company
	 * @param wechatpubinfoid
	 * @return
	 */
	@RequestMapping("/company_info")
	public IRespParam companyInfo(@ModelAttribute Company company,
			@ModelAttribute(AppConstants.WECHATPUBINFOID) int wechatpubinfoid) {
		int companyID = company.getCompanyID();
		String parameterName = "iscustomer";
		Parameter parameter = parameterRepository.findFirstByCompanyIDAndParameterName(companyID, parameterName);
		if(parameter != null){
			String value = parameter.getParameterValue();
			DynamicRespParam respParam = new DynamicRespParam();
			respParam.set("iscustomer", TypeCaseHelper.convert2Integer(value));
			//是否开启消息模版服务 0-未开启 1-已开启, 现在搞成未开启
			respParam.set("ismessagemodel", 0);
			return respParam;
		}else{
			return RespUtils.notExist();
		}
		
	}
}
