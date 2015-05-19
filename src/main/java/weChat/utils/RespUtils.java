package weChat.utils;

import static weChat.utils.RespMsgCode.*;
import weChat.parameter.manage.MResponseParam;

/**
 * 返回消息工具类
 * 
 * @author deng
 * @date 2015年4月23日
 * @version 1.0.0
 */
public class RespUtils {
	/**
	 * 管理系统数据同步接口（管理系统发起）的成功返回参数
	 * 
	 * @return
	 */
	public static MResponseParam successMR() {
		return new MResponseParam(SUCCESS_CODE, "");
	}
	/**
	 * 管理系统数据同步接口数据错误
	 * @param msg
	 * @return
	 */
	public static MResponseParam parameterError(String msg){
		return new MResponseParam(ARGUMENT_NOT_VALID,msg);
	}

}
