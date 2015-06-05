package weChat.service;

import org.springframework.scheduling.annotation.Async;

import weChat.parameter.impl.RReqParam;

/**
 * 专门用于执行异步线程的
 * @author deng
 * @date 2015年6月5日
 * @version 1.0.0
 */
public interface AsyncService {
	/**
	 * 把会员信息修改同步K米
	 * @param param
	 * @throws Exception
	 */
	@Async
	public void syncKM(RReqParam param) throws Exception;
}
