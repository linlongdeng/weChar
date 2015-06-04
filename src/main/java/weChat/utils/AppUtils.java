package weChat.utils;
/**
 * 一些方法集合
 * @author deng
 * @date 2015年6月4日
 * @version 1.0.0
 */
public abstract class AppUtils {
	/**
	 * 判断code是否和success_code相等，如果不等，返回false,如果相等，返回true
	 * @param code
	 * @return
	 */
	public static  boolean checkSuccess(int code){
		return RespMsgCode.SUCCESS_CODE == code;
	}

}
