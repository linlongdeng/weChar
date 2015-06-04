package weChat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;

import weChat.core.metatype.BaseDto;
import weChat.core.rabbit.RabbitClient;
import weChat.core.rabbit.RabbitClientConfig;
import weChat.core.utils.CommonUtils;
import weChat.core.utils.ValidationUtils;
import weChat.domain.primary.Company;
import weChat.domain.primary.Kmbindcard;
import weChat.domain.primary.MemberCache;
import weChat.parameter.IRespParam;
import weChat.parameter.impl.CommonParam;
import weChat.parameter.impl.KRespResParam;
import weChat.parameter.impl.RReqParam;
import weChat.repository.primary.CompanyRepository;
import weChat.repository.primary.KmbindcardRepository;
import weChat.repository.primary.MemberCacheRepository;
import weChat.service.InvokeKmService;
import weChat.service.ValidationService;
import weChat.service.WechatMqService;
import weChat.utils.AppConstants;
import weChat.utils.AppUtils;

@Service("WJ005" + AppConstants.WJMQ_SUFFIX)
public class WechatMqWJ005ServiceImpl extends WechatMqService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberCacheRepository memberCacheRepository;
	@Autowired
	private KmbindcardRepository kmbindcardRepository;
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private InvokeKmService invokeKmService;

	@Autowired
	public WechatMqWJ005ServiceImpl(RabbitClient rabbitClient,
			RabbitClientConfig config, ValidationService validationService) {
		super(rabbitClient, config, validationService);
	}

	@Override
	public IRespParam handle(RReqParam param) throws Exception {
		CommonParam resp = (CommonParam) super.handle(param);
		Integer ret = resp.getAsInteger("ret");
		// 判断MQ结果是否成功
		if (AppUtils.checkSuccess(ret)) {
			String companycode = param.getCompanycode();
			Company company = companyRepository
					.findFirstByCompanyCode(companycode);
			Assert.notNull(company, "商家不存在");
			BaseDto pDto = param.getParams();
			String memberid = pDto.getAsString("memberid");
			String kmid = pDto.getAsString("kmid");
			// K米不能为空
			if (CommonUtils.isNotEmpty(kmid)) {
				MemberCache member = memberCacheRepository
						.findTopByCompanyIDAndMemberid(company.getCompanyID(),
								memberid);
				if (member != null) {
					// 会员状态，必须为启用
					if (AppConstants.MEMBER_STATUS_USER.equals(member
							.getStatus())) {
						Kmbindcard kmbindcard = kmbindcardRepository
								.findFirstByKmid(kmid);
						if (kmbindcard != null) {
							// K米APP用户ID
							int customerid = kmbindcard.getCustomerID();
							pDto.put("customerid", customerid);
							KRespResParam kRespResParam = (KRespResParam) invokeKmService
									.fillCustomerInfo(pDto);
							ret = kRespResParam.getRet();
							if (AppUtils.checkSuccess(ret)) {
								logger.info("会员资料( {}:{} )同步到K米成功",
										companycode, memberid);
							} else {
								logger.warn("会员资料( {}:{} )同步到K米失败",
										companycode, memberid);
							}
						}

					}
				}
			}

		}
		return resp;

	}

	@Override
	public void validate(Object target, Errors e) {
		RReqParam rreqParam = (RReqParam) target;
		BaseDto params = rreqParam.getParams();
		ValidationUtils.rejectIfEmpty(params, "param", e, "memberid",
				"membername", "sex", "mobile", "birthday");
	}

}
