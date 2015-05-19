package weChat.service;

import javax.transaction.Transactional;

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
	public MResponseParam memberLevel(MRequestParam param);
	
	
	/**
	 * 会员信息同步
	 * @param param
	 * @return
	 */
	@Transactional
	public MResponseParam memberInfo(MRequestParam param);
}
