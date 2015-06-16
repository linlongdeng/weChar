package weChat.service;

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
}
