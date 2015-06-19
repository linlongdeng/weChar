package weChat.service.km.impl;

import static weChat.core.utils.CommonUtils.isNotEmpty;
import static weChat.utils.AppConstants.KM_BIND_CARD_SOURCE_KM;
import static weChat.utils.AppConstants.KM_BIND_CARD_STATUS_BIND;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import weChat.core.metatype.BaseDto;
import weChat.core.metatype.Dto;
import weChat.core.utils.CommonUtils;
import weChat.domain.primary.Company;
import weChat.domain.primary.Kmbindcard;
import weChat.domain.primary.MemberCache;
import weChat.domain.primary.Parameter;
import weChat.parameter.IRespParam;
import weChat.parameter.amqp.AmqpReqParam;
import weChat.parameter.common.CommonParam;
import weChat.parameter.common.DynamicRespParam;
import weChat.parameter.manager.MRespParam;
import weChat.repository.primary.CompanyRepository;
import weChat.repository.primary.KmbindcardRepository;
import weChat.repository.primary.MemberCacheRepository;
import weChat.repository.primary.ParameterRepository;
import weChat.service.amqp.WechatMqService;
import weChat.service.common.RespService;
import weChat.service.km.KmService;
import weChat.utils.AppConstants;
import weChat.utils.AppUtils;

@Service
public class KmServiceImpl implements KmService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberCacheRepository memberCacheRepository;
	@Autowired
	private RespService respService;
	@Resource(name = "WJ007" + AppConstants.WJMQ_SUFFIX)
	private WechatMqService wj007Service;

	@Resource(name = "WJ008" + AppConstants.WJMQ_SUFFIX)
	private WechatMqService wj008Service;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ParameterRepository parameterRepository;
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private KmbindcardRepository kmbindcardRepository;

	@Override
	public IRespParam bindCardInfo(Company company, Dto otherParam,
			int wechatpubinfoid) throws Exception {
		String cardnum = otherParam.getAsString("cardnum");
		MemberCache memberCache = memberCacheRepository
				.findTopByCardnumAndStatusAndWechatPubInfoID(cardnum,
						AppConstants.MEMBER_STATUS_USER, wechatpubinfoid);
		// 验证商家会员信息
		AppUtils.assertMemberCacheNull(memberCache);
		// 要根据商家公众号和卡号来获取实际绑卡的商家，在总分店模式下，K米传上来的商家可能有误
		int companyID = memberCache.getCompanyID();
		Company memberCompany = companyRepository.findOne(companyID);
		// 会员建卡分店信息查询校验
		AppUtils.assertCompanyNotNull(memberCompany);
		// 后面用会员建卡分店的商家信息
		AmqpReqParam mqParam = new AmqpReqParam();
		mqParam.setCmdid("WJ007");
		mqParam.setCompanycode(memberCompany.getCompanyCode());
		mqParam.setWechatpubinfoid(wechatpubinfoid);
		BaseDto mqDto = new BaseDto();
		mqDto.put("cardnum", cardnum);
		mqParam.setParams(mqDto);
		// 调用MQ服务
		CommonParam mqResp = (CommonParam) wj007Service.handle(mqParam);
		Integer ret = mqResp.getAsInteger("ret");
		// 判断请求是否成功
		if (AppUtils.checkSuccess(ret)) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) mqResp.get("data");
			Object mobile = map.get("mobile");
			DynamicRespParam resp = new DynamicRespParam();
			resp.set("mobile", mobile);
			return resp;
		} else {
			return new MRespParam(ret, mqResp.getMsg());
		}

	}

	@Override
	public IRespParam memberInfo(int customerid) {
		// 采用原生JDBC执行SQL语句
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				MemberCacheRepository.MEMBER_INFO_SQL, customerid);
		List<Map<String, Object>> dataList = new ArrayList<>();
		if (isNotEmpty(list)) {
			Parameter parameter = parameterRepository
					.findFirstByParameterName(AppConstants.FILE_DWONLOAD_PATH);
			String fileDownload = parameter.getParameterValue();
			for (Map<String, Object> memberCacheInfo : list) {
				Map<String, Object> item = new HashMap<>();
				String cardpicurl = "";
				if (isNotEmpty(memberCacheInfo.get("CardPicID"))) {
					cardpicurl = fileDownload + "?fileid="
							+ memberCacheInfo.get("CardPicID");
				}
				item.put("cardpicurl", cardpicurl);
				item.put("kmid", memberCacheInfo.get("KMID"));
				item.put("companyid", memberCacheInfo.get("CompanyID"));
				item.put("membername", memberCacheInfo.get("MemberName"));
				item.put("birthday", memberCacheInfo.get("birthday"));
				item.put("sex", memberCacheInfo.get("sex"));
				item.put("papertype", memberCacheInfo.get("PaperType"));
				item.put("papernumber", memberCacheInfo.get("PaperNumber"));
				item.put("address", "");
				item.put("cardnum", memberCacheInfo.get("Cardnum"));
				item.put("createcardtime",
						memberCacheInfo.get("CreateCardTime"));
				item.put("gradename", memberCacheInfo.get("GradeName"));
				item.put("status", memberCacheInfo.get("status"));
				item.put("mobile", memberCacheInfo.get("mobile"));
				item.put("email", "");// 暂时没有‘邮箱’信息，默认为‘’
				item.put("uselimitdate", memberCacheInfo.get("UseLimitDate"));
				item.put("cardintegral", memberCacheInfo.get("IntegralBalance"));
				item.put("accountbalance",
						memberCacheInfo.get("AccountBalance"));
				item.put("accountcash", memberCacheInfo.get("AccountCash"));
				item.put("accountpresent",
						memberCacheInfo.get("AccountPresent"));
				item.put("accountoverdraft", 0);// 暂时没有‘可透支限额’信息，默认为0
				item.put("lastconsumetime",
						memberCacheInfo.get("LastConsumeTime"));
				item.put("updatetime", memberCacheInfo.get("UpdateTime"));
				item.put("note", ""); // 备注，默认为空
				dataList.add(item);
			}
		}
		DynamicRespParam resp = new DynamicRespParam();
		resp.set("data", dataList);
		return resp;
	}

	@Override
	public IRespParam bindCard(int wechatpubinfoid, int customerid,
			Dto otherParam) throws Exception {
		String cardnum = otherParam.getAsString("cardnum");
		MemberCache memberCache = memberCacheRepository
				.findTopByCardnumAndStatusAndWechatPubInfoID(cardnum,
						AppConstants.MEMBER_STATUS_USER, wechatpubinfoid);
		// 验证商家会员信息
		AppUtils.assertMemberCacheNull(memberCache);
		int companyid = memberCache.getCompanyID();
		Company memberCompany = companyRepository.findOne(companyid);
		// 会员建卡分店信息查询校验
		AppUtils.assertCompanyNotNull(memberCompany);
		String companycode = memberCompany.getCompanyCode();
		String kmid = memberCache.getKmid();
		// 已经绑过卡，只维护绑卡关系
		if (CommonUtils.isNotEmpty(kmid)) {
			// 该卡其他人是否绑过，和该会员是否绑过其他卡,全部解绑,然后新增
			bindMemberRelation(companyid, customerid, kmid);
			DynamicRespParam resp = new DynamicRespParam();
			resp.set("kmid", kmid);
			return resp;

		}
		// 未绑过卡，通知线下系统绑卡
		else {
			//调用MQ，通知线下绑卡
			CommonParam mqResp = invokeMQBindCard(companycode, wechatpubinfoid,
					cardnum, otherParam.getAsString("moblie"));
			Integer ret = mqResp.getAsInteger("ret");
			// 判断请求是否成功
			if (AppUtils.checkSuccess(ret)) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) mqResp
						.get("data");
				kmid = (String) map.get("kmid");
				// 该卡其他人是否绑过，和该会员是否绑过其他卡,全部解绑,然后新增
				bindMemberRelation(companyid, customerid, kmid);
				// 更新会员K米ID
				updateMemberKmId(cardnum, companyid, kmid);
				// 更新会员表的K米ID
				DynamicRespParam resp = new DynamicRespParam();
				resp.set("kmid", kmid);
				return resp;
			} else {
				return new MRespParam(ret, mqResp.getMsg());
			}

		}
	}

	/**
	 * 解绑原来的绑定关系，新增K米APP绑卡关系信息
	 * 
	 * @param companyID
	 * @param customerid
	 * @param kmid
	 */
	private void bindMemberRelation(int companyid, int customerid, String kmid) {
		// 该卡其他人是否绑过，和该会员是否绑过其他卡,全部解绑,然后新增
		kmbindcardRepository.setUnbindByKmid(companyid, kmid);
		kmbindcardRepository.setUnbindByOrCustomerID(companyid, customerid);
		Kmbindcard kmbindcard = new Kmbindcard();
		kmbindcard.setOpenID("");
		kmbindcard.setKmid(kmid);
		kmbindcard.setBindTime(CommonUtils.currentTimestamp());
		kmbindcard.setCompanyID(companyid);
		kmbindcard.setCustomerID(customerid);
		kmbindcard.setStatus(KM_BIND_CARD_STATUS_BIND);
		// K米绑卡
		kmbindcard.setSource(KM_BIND_CARD_SOURCE_KM);
		// 保存K米APP绑卡关系信息
		kmbindcardRepository.save(kmbindcard);
	}

	/**
	 * 更新会员K米ID
	 * 
	 * @param cardnum
	 * @param companyid
	 * @param kmid
	 */
	private void updateMemberKmId(String cardnum, int companyid, String kmid) {
		// 更新会员的kmid
		MemberCache memberCache = memberCacheRepository
				.findTopByCardnumAndCompanyID(cardnum, companyid);
		// 判断根据卡号和商家ID获取到的会员信息是否为空
		AppUtils.assertMemberCacheKmNull(memberCache);
		memberCache.setKmid(kmid);
	}

	/**
	 * 通知MQ绑卡
	 * 
	 * @param companycode
	 * @param wechatpubinfoid
	 * @param cardnum
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	private CommonParam invokeMQBindCard(String companycode,
			int wechatpubinfoid, String cardnum, String mobile)
			throws Exception {
		AmqpReqParam mqParam = new AmqpReqParam();
		mqParam.setCmdid("WJ008");
		mqParam.setCompanycode(companycode);
		mqParam.setWechatpubinfoid(wechatpubinfoid);
		BaseDto mqDto = new BaseDto();
		mqDto.put("cardnum", cardnum);
		mqDto.put("kmid", "");
		mqDto.put("validatetype", "1");
		mqDto.put("mobile", mobile);
		// 说明是K米绑卡
		mqDto.put("iskm", true);
		mqParam.setParams(mqDto);
		// 调用MQ服务
		CommonParam mqResp = (CommonParam) wj008Service.handle(mqParam);
		return mqResp;
	}
}
