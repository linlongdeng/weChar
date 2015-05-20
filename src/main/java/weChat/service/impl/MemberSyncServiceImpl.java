package weChat.service.impl;

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
import weChat.domain.primary.Company;
import weChat.domain.primary.Gradecollect;
import weChat.domain.primary.MemberCache;
import weChat.parameter.manage.MRequestParam;
import weChat.parameter.manage.MResponseParam;
import weChat.repository.primary.CompanyRepository;
import weChat.repository.primary.GradecollectRepository;
import weChat.repository.primary.MemberCacheRepository;
import weChat.service.MemberSyncService;
import weChat.service.ValidationService;
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
	public MResponseParam memberLevel(MRequestParam param) {
		List<BaseDto> data = param.getData();
		String companycode = param.getCompanycode();
		Company company = companyRepository.findFirstByCompanyCode(companycode);
		Assert.notNull(company, "商家编码不存在");
		int wechatpubinfoid = param.getWechatpubinfoid();
		if (data != null) {
			for (BaseDto dto : data) {
				Integer gradeid = dto.getAsInteger("gradeid");
				Gradecollect gradecollect = gradecollectRepository
						.findFirstByCompanyCodeAndWechatPubInfoIDAndGradeID(
								companycode, wechatpubinfoid, gradeid);
				String gradecode = dto.getAsString("gradecode");
				String gradename = dto.getAsString("gradename");
				Integer status = dto.getAsInteger("status");
				if (gradecollect == null) {
					gradecollect = new Gradecollect();
					// 有问题
					gradecollect.setCompanyID(company.getCompanyID());
					// 格式不对
					gradecollect.setWechatPubInfoID(Integer
							.valueOf(wechatpubinfoid));
					// 格式不对
					gradecollect.setGradeID(gradeid);
					gradecollect.setCreateTime(new Date());
				}
				gradecollect.setGradeCode(gradecode);
				gradecollect.setGradeName(gradename);
				gradecollect.setStatus(status.byteValue());
				gradecollect.setUpdateTime(new Date());
				gradecollectRepository.save(gradecollect);
			}
		}
		return RespUtils.successMR();
	}

	@Override
	public MResponseParam memberInfo(MRequestParam param) {
		String companycode = param.getCompanycode();
		int wechatpubinfoid = param.getWechatpubinfoid();
		Company company = companyRepository.findFirstByCompanyCode(companycode);
		Assert.notNull(company, "商家编码不存在");
		StringBuffer sb = new StringBuffer();
		int companyID = company.getCompanyID();
		List<BaseDto> data = param.getData();
		if (data != null) {
			List<MemberCache> list = new ArrayList<MemberCache>();
			for (BaseDto dto : data) {
				String kmid = dto.getAsString("kmid");
				String memberid = dto.getAsString("memberid");
				MemberCache memberCache = memberCacheRepository
						.findTopByCompanyIDAndMemberid(companyID, memberid);
				if (memberCache == null) {
					memberCache = new MemberCache();
					memberCache.setCompanyID(companyID);
					memberCache.setMemberid(dto.getAsString("memberid"));
				}
				memberCache.setWechatPubInfoID(wechatpubinfoid);
				memberCache.setKmid(dto.getAsString("kmid"));
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
				memberCache.setCreateTime(new Date());
				memberCache.setUpdateTime(new Date());
				//校验数据，如果出错的话，自动抛出异常，中止数据同步
				validationService.validate(memberCache, "data");
				list.add(memberCache);
			}
			// 保存信息
			memberCacheRepository.save(list);
		}
		return RespUtils.successMR();
	}

}
