package weChat.service.km.impl;

import static weChat.utils.AppConstants.COMPANY;
import static weChat.utils.AppConstants.OTHER_PARAM;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import weChat.core.metatype.BaseDto;
import weChat.core.metatype.Dto;
import weChat.domain.primary.Company;
import weChat.domain.primary.Companywechatpub;
import weChat.domain.primary.MemberCache;
import weChat.parameter.IRespParam;
import weChat.parameter.amqp.AmqpReqParam;
import weChat.parameter.common.CommonParam;
import weChat.parameter.common.DynamicRespParam;
import weChat.parameter.manager.MRespParam;
import weChat.repository.primary.CompanywechatpubRepository;
import weChat.repository.primary.MemberCacheRepository;
import weChat.service.amqp.WechatMqService;
import weChat.service.common.RespService;
import weChat.service.km.KmService;
import weChat.service.km.KmbindcardService;
import weChat.utils.AppConstants;
import weChat.utils.AppUtils;

@Service
public class KmServiceImpl implements KmService {
	@Autowired
	private MemberCacheRepository memberCacheRepository;
	@Autowired
	private RespService respService;
	@Resource(name="WJ007" + AppConstants.WJMQ_SUFFIX)
	private WechatMqService wj008Service;

	@Override
	public IRespParam bindCardInfo(Company company, Dto otherParam,
			int wechatpubinfoid) throws Exception {
		String cardnum = otherParam.getAsString("cardnum");

		MemberCache memberCache = memberCacheRepository
				.findTopByCardnumAndStatusAndWechatPubInfoID(cardnum,
						AppConstants.MEMBER_STATUS_USER, wechatpubinfoid);
		if (memberCache != null) {
			AmqpReqParam mqParam = new AmqpReqParam();
			mqParam.setCmdid("WJ007");
			mqParam.setCompanycode(company.getCompanyCode());
			mqParam.setWechatpubinfoid(wechatpubinfoid);
			BaseDto mqDto = new BaseDto();
			mqDto.put("cardnum", cardnum);
			mqParam.setParams(mqDto);
			// 调用MQ服务
			CommonParam mqResp = (CommonParam) wj008Service.handle(mqParam);
			Integer ret = mqResp.getAsInteger("ret");
			// 判断请求是否成功
			if (AppUtils.checkSuccess(ret)) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) mqResp
						.get("data");
				Object mobile = map.get("mobile");
				DynamicRespParam resp = new DynamicRespParam();
				resp.set("mobile", mobile);
				return resp;
			} else {
				return new MRespParam(ret, mqResp.getMsg());
			}

		} else {
			//商家会员信息验证失败
			return respService.noMember(cardnum);
		}

	}

}
