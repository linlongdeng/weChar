package weChat.service.km;


import static weChat.utils.AppConstants.*;

import org.springframework.transaction.annotation.Transactional;

import weChat.core.metatype.Dto;
import weChat.domain.primary.Company;
import weChat.parameter.IRespParam;

public interface KmService {
/**
 * K米APP获取绑卡信息
 * @param company
 * @param otherParam
 * @param wechatpubinfoid
 * @return
 */
	public IRespParam bindCardInfo(Company company, Dto otherParam,
			int wechatpubinfoid) throws Exception;
	
	/**
	 * K米APP批量获取会员信息
	 * @param customerid
	 * @return
	 */
	public IRespParam memberInfo(int customerid);
	/**
	 * K米APP绑卡
	 * @param wechatpubinfoid
	 * @param customerid
	 * @param otherParam
	 * @return
	 */
	@Transactional
	public IRespParam bindCard(int wechatpubinfoid,
			 int customerid,
		 Dto otherParam)  throws Exception;
}
