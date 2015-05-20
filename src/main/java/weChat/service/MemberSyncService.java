package weChat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.ModelAttribute;

import weChat.core.metatype.BaseDto;
import weChat.core.metatype.Dto;
import weChat.domain.primary.Company;
import weChat.parameter.manage.MRequestParam;
import weChat.parameter.manage.MResponseParam;

/**会员等级服务
 * @author deng
 * @date 2015年4月23日
 * @version 1.0.0
 */
public interface MemberSyncService {
	/**
	 * 同步会员等级
	 * @param param
	 * @return
	 */
	@Transactional
	public MResponseParam memberLevel(Company company, int wechatpubinfoid, List<Dto> data);
	
	
	/**
	 * 会员信息同步
	 * @param param
	 * @return
	 */
	@Transactional
	public MResponseParam memberInfo(Company company, int wechatpubinfoid, List<Dto> data);
}
