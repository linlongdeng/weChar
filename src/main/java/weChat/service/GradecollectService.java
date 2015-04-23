package weChat.service;

import javax.transaction.Transactional;

import weChat.parameter.manage.MRequestParam;
import weChat.parameter.manage.MResponseParam;

/**会员等级服务
 * @author deng
 * @date 2015年4月23日
 * @version 1.0.0
 */
public interface GradecollectService {
	/**
	 * 同步会员等级
	 * @param param
	 * @return
	 */
	@Transactional
	public MResponseParam syncGrade(MRequestParam param);
}
