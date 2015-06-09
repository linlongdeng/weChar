package weChat.utils;

import org.springframework.util.Assert;

import weChat.domain.primary.Company;
import weChat.domain.primary.Wechatpubinfo;

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
	
	public static void assertCompanyNotNull(Company company){
		Assert.notNull(company, "商家信息不能为空");
	}
	/**
	 * 商家密码是否正确
	 * @param companypsw
	 * @param company
	 */
	public static void assertCompanyPassError( String companypsw, Company company){
		Assert.isTrue(company.getCompanyPsw().equals(companypsw),"商家密码错误");
	}
	/**
	 * 微信公众号是否存在
	 * @param wechatpubinfo
	 */
	public static void assertWechatpubinfoNotNull(Wechatpubinfo wechatpubinfo){
		Assert.notNull(wechatpubinfo, "微信公众号不存在");
	}

}
