package weChat.service.manage.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.hibernate.jpa.internal.EntityManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import weChat.core.metatype.BaseDto;
import weChat.core.metatype.Dto;
import weChat.core.utils.CommonUtils;
import weChat.domain.primary.Company;
import weChat.domain.primary.Gradecollect;
import weChat.domain.primary.MemberCache;
import weChat.parameter.IRespParam;
import weChat.parameter.manager.MReqParam;
import weChat.parameter.manager.MRespParam;
import weChat.repository.primary.CompanyRepository;
import weChat.repository.primary.GradecollectRepository;
import weChat.repository.primary.MemberCacheRepository;
import weChat.service.common.ValidationService;
import weChat.service.manage.MemberSyncService;
import weChat.utils.RespUtils;

@Service
public class MemberSyncServiceImpl implements MemberSyncService {

	@Autowired
	private GradecollectRepository gradecollectRepository;
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private MemberCacheRepository memberCacheRepository;

	@Autowired
	private ValidationService validationService;

	public static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

	@Override
	public IRespParam memberLevel(Company company, int wechatpubinfoid, List<Dto> data) {
		String companyCode = company.getCompanyCode();
		if (data != null) {
			for (Dto dto : data) {
				Integer gradeid = dto.getAsInteger("gradeid");
				Gradecollect gradecollect = gradecollectRepository
						.findFirstByCompanyCodeAndWechatPubInfoIDAndGradeID(
								companyCode, wechatpubinfoid, gradeid);
				String gradecode = dto.getAsString("gradecode");
				String gradename = dto.getAsString("gradename");
				Integer status = dto.getAsInteger("status");
				if (gradecollect == null) {
					gradecollect = new Gradecollect();
					// 有问题
					gradecollect.setCompanyID(company.getCompanyID());
					// 格式不对
					gradecollect.setWechatPubInfoID(wechatpubinfoid);
					// 格式不对
					gradecollect.setGradeID(gradeid);
					gradecollect.setCreateTime(new Date());
				}
				gradecollect.setGradeCode(gradecode);
				gradecollect.setGradeName(gradename);
				gradecollect.setStatus(status.byteValue());
				gradecollect.setUpdateTime(new Date());
				//新增可以储值、可以积分、是否可以在线申请会员
				gradecollect.setUseStorage(dto.getAsByte("usestorage"));
				gradecollect.setUseIntegral(dto.getAsByte("useintegral"));
				//是否可以在线申请会员线下还没有开发，暂时屏蔽
				//gradecollect.setUseOnlineApp(dto.getAsByte("useonlineapp"));
				gradecollectRepository.save(gradecollect);
			}
		}
		return RespUtils.successMR();
	}

	@Override
	public IRespParam memberInfo(Company company, int wechatpubinfoid, List<Dto> data) {

		StringBuffer sb = new StringBuffer();
		int companyID = company.getCompanyID();
		if (data != null) {
			List<MemberCache> list = new ArrayList<MemberCache>();
			for (Dto dto : data) {
				//TODO KM id 有问题,作兼容
				String kmid = dto.containsKey("KmID") ?  dto.getAsString("KmID") : dto.getAsString("kmid");
				String memberid = dto.getAsString("memberid");
				MemberCache memberCache = memberCacheRepository
						.findTopByCompanyIDAndMemberid(companyID, memberid);
				String status = dto.getAsString("status");
				//只有删除状态的数据只更新状态
				if("删除".equals(status)){
					//非启用状态的数据只有存在才更新，不存在不更新
					if(memberCache != null){
						memberCache.setStatus(status);
						memberCache.setUpdateTime(new Date());
						//会员创建时间如果有空，要设置，因为该字段不能为空
						if(memberCache.getCreateTime() == null){
							memberCache.setCreateTime(new Date());
						}
						list.add(memberCache);
					}
				}else{
					if (memberCache == null) {
						memberCache = new MemberCache();
						memberCache.setCompanyID(companyID);
						memberCache.setMemberid(dto.getAsString("memberid"));
						memberCache.setCreateTime(new Date());
					}
					memberCache.setWechatPubInfoID(wechatpubinfoid);
					//会员创建时间如果有空，要设置，因为该字段不能为空
					if(memberCache.getCreateTime() == null){
						memberCache.setCreateTime(new Date());
					}

					memberCache.setKmid(kmid);
					memberCache.setGradeID(dto.getAsInteger("gradeid"));
					memberCache.setCardnum(dto.getAsString("cardnum"));
					memberCache.setMemberName(dto.getAsString("membername"));
					memberCache.setBirthday(dto.getAsDate("birthday"));
					memberCache.setSex(dto.getAsString("sex"));
					memberCache.setPaperType(dto.getAsString("papertype"));
					memberCache.setPaperNumber(dto.getAsString("papernumber"));
					memberCache.setCreateCardTime(dto.getAsDate("createcardtime",
							dateFormat));
					memberCache.setMemberPsw(dto.getAsString("memberpsw"));
					memberCache.setStatus(dto.getAsString("status"));
					memberCache.setMobile(dto.getAsString("mobile"));
					memberCache.setUseLimitDate(dto.getAsDate("useLimitdate"));
					memberCache.setAccountCash(dto.getAsBigDecimal("accountcash"));
					memberCache.setAccountPresent(dto
							.getAsBigDecimal("accountpresent"));
					memberCache.setIntegralBalance(dto
							.getAsBigDecimal("integralbalance"));
					memberCache
							.setConsumeTotal(dto.getAsBigDecimal("consumetotal"));
					memberCache.setAccountBalance(dto
							.getAsBigDecimal("accountbalance"));
					memberCache.setIntegralBalance(dto
							.getAsBigDecimal("integralbalance"));
					memberCache.setConsumeTimes(dto.getAsInteger("consumetimes"));
					memberCache.setLastConsumeTime(dto.getAsDate("lastconsumetime",
							dateFormat));
					memberCache.setUpdateTime(new Date());
					//校验数据，如果出错的话，自动抛出异常，中止数据同步
					validationService.validate(memberCache, "data");
					list.add(memberCache);
				}
			
			}
			// 保存信息
			memberCacheRepository.save(list);
		}
		return RespUtils.successMR();
	}

}
