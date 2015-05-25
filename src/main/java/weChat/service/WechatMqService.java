package weChat.service;

import weChat.parameter.IRespParam;
import weChat.parameter.impl.RReqParam;

public interface WechatMqService {

	/**
	 * 校验数据
	 * @param param
	 */
	public void validate(RReqParam param);
	/**
	 * 处理数据
	 * @param param
	 */
	public IRespParam handle(RReqParam param);
}
