package weChat.utils;

/**
 * 存在一些常量
 * 
 * @author deng
 * @date 2015年5月21日
 * @version 1.0.0
 */
public class AppConstants {
	/**斜杠**/
	public static final String SLASH="/";
	/** 商家 **/
	public static final String COMPANY = "company";
	/** 公众号ID **/
	public static final String WECHATPUBINFOID = "wechatpubinfoid";
	/**数据**/
	public static final String DATA= "data";
	/**
	 * 后缀
	 */
	public static final String WJMQ_SUFFIX="wj_service";
	/**
	 * wj mq服务cmdid
	 */
	public static final String WJMQ_CMDID ="cmdid";
	/**wj mq服务的具体参数名称 **/
	public static final String WJMQ_PARAM = "param";
	/**参数错误**/
	public static final String ARGUMENT_NOT_VALID="ARGUMENT_NOT_VALID_INFO";
	/**参数不能为空**/
	public static final String ARGUMENT_NOT_EMPTY_INFO ="ARGUMENT_NOT_EMPTY_INFO";
	/**会员状态：启用**/
	public static final String MEMBER_STATUS_USER="启用";
	/**微信用户绑定绑卡状态**/
	public static final byte WEIXIN_USER_BIND_STATUS_BIND=1;
	/**K米APP绑卡关系表, 状态 1绑卡 2取消绑卡**/
	public static final byte  KM_BIND_CARD_STATUS_BIND=1;
	/**取消绑卡**/
	public static final byte KM_BIND_CARD_STATUS_UNBIND=2;
	/**K米APP绑卡关系表的绑卡来源，微信绑卡**/
	public static final byte KM_BIND_CARD_SOURCE_WEIXIN =1;
	/**微信绑卡进程休息时间**/
	public static final long SLEEP_MILLIS= 3000;
	
	
}
