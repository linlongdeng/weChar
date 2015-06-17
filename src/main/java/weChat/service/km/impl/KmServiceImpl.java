package weChat.service.km.impl;

import static weChat.utils.AppConstants.COMPANY;
import static weChat.utils.AppConstants.OTHER_PARAM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import weChat.core.metatype.BaseDto;
import weChat.core.metatype.Dto;
import static weChat.core.utils.CommonUtils.*;
import weChat.domain.primary.Company;
import weChat.domain.primary.Companywechatpub;
import weChat.domain.primary.MemberCache;
import weChat.domain.primary.Parameter;
import weChat.parameter.IRespParam;
import weChat.parameter.amqp.AmqpReqParam;
import weChat.parameter.common.CommonParam;
import weChat.parameter.common.DynamicRespParam;
import weChat.parameter.manager.MRespParam;
import weChat.repository.primary.CompanywechatpubRepository;
import weChat.repository.primary.MemberCacheRepository;
import weChat.repository.primary.ParameterRepository;
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
	@Resource(name = "WJ007" + AppConstants.WJMQ_SUFFIX)
	private WechatMqService wj008Service;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ParameterRepository parameterRepository;

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
			// 商家会员信息验证失败
			return respService.noMember(cardnum);
		}

	}

	@Override
	public IRespParam memberInfo(int customerid) {
		String sql = "SELECT mc.KMID, mc.CompanyID, mc.MemberName, mc.birthday, mc.sex, mc.PaperType, mc.PaperNumber, mc.Cardnum, mc.CreateCardTime,"
				+ " mc.GradeID, mc.`status`, mc.mobile, mc.UseLimitDate, mc.IntegralBalance, mc.AccountBalance, mc.AccountCash, mc.AccountPresent, mc.LastConsumeTime, mc.UpdateTime, "
				+ "g.GradeName, g.CardPicID FROM wj_tbl_kmbindcard AS kbc INNER JOIN wj_tbl_member_cache AS mc ON kbc.Kmid = mc.KMID AND kbc. STATUS = 1 AND mc. STATUS = '启用' AND kbc.CustomerID = ? LEFT JOIN wj_tbl_gradecollect "
				+ " AS g ON mc.GradeID = g.GradeID AND mc.CompanyID = g.CompanyID";
		//采用原生JDBC执行SQL语句
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,
				customerid);
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

}
