package weChat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import weChat.core.metatype.BaseDto;
import weChat.core.utils.CommonUtils;
import weChat.domain.primary.Company;
import weChat.domain.primary.Kmbindcard;
import weChat.domain.primary.MemberCache;
import weChat.parameter.impl.KRespResParam;
import weChat.parameter.impl.RReqParam;
import weChat.repository.primary.CompanyRepository;
import weChat.repository.primary.KmbindcardRepository;
import weChat.repository.primary.MemberCacheRepository;
import weChat.service.AsyncService;
import weChat.service.InvokeKmService;
import weChat.utils.AppConstants;
import weChat.utils.AppUtils;

@Service
public class AsyncServiceImpl implements AsyncService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberCacheRepository memberCacheRepository;
	@Autowired
	private KmbindcardRepository kmbindcardRepository;
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private InvokeKmService invokeKmService;

	@Override
	public void syncKM(RReqParam param) throws Exception {
		String companycode = param.getCompanycode();
		Company company = companyRepository.findFirstByCompanyCode(companycode);
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
				if (AppConstants.MEMBER_STATUS_USER.equals(member.getStatus())) {
					Kmbindcard kmbindcard = kmbindcardRepository
							.findFirstByKmid(kmid);
					if (kmbindcard != null) {
						logger.debug("开始同步会员信息修改到K米{}:{}",companycode,memberid);
						// K米APP用户ID
						int customerid = kmbindcard.getCustomerID();
						pDto.put("customerid", customerid);
						KRespResParam kRespResParam = (KRespResParam) invokeKmService
								.fillCustomerInfo(pDto);
						int ret = kRespResParam.getRet();
						if (AppUtils.checkSuccess(ret)) {
							logger.info("会员资料( {}:{} )同步到K米成功", companycode,
									memberid);
						} else {
							logger.warn("会员资料( {}:{} )同步到K米失败,原因{}",
									companycode, memberid, kRespResParam);
						}
					}

				}
			}
		}

	}

}
