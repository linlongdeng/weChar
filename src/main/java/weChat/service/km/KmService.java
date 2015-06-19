package weChat.service.km;

import java.util.ArrayList;
import java.util.Map;

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
	
	/**根据电子会员卡（KMID）获取会员信息
	 * @param otherParam
	 * @return
	 * @throws Exception
	 */
	public IRespParam memberInfoByKmID( String kmid) throws Exception;

	/**获取参数信息
	 * @param paramers 参数名称
	 * @return
	 * @throws Exception
	 */
	public IRespParam getParamer(ArrayList<String> paramers) throws Exception;
	
	/**更新参数
	 * @return
	 * @throws Exception
	 */
	public IRespParam updateParamer(int companyID,ArrayList<Map<String, String>> paramerEntrys) throws Exception;
	
	
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
