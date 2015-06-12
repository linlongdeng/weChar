package weChat.parameter.impl;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * K米APP获取授权码请求参数
 * 
 * @author deng
 * @date 2015年6月11日
 * @version 1.0.0
 */
public class KAuthReqParam {

	@NotNull
	@Pattern(regexp = "^kmapp$", message = "granttype参数值非法")
	private String granttype;
	@NotNull
	private Integer appid;
	@NotNull
	private String appkey;

	public String getGranttype() {
		return granttype;
	}

	public void setGranttype(String granttype) {
		this.granttype = granttype;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public Integer getAppid() {
		return appid;
	}

	public void setAppid(Integer appid) {
		this.appid = appid;
	}

}
