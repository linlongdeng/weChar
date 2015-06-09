package weChat.parameter.impl;

import javax.validation.constraints.NotNull;

import weChat.parameter.IReqParam;

public class WReqParam implements IReqParam {

	@NotNull
	private Integer wechatpubinfoid;

	public Integer getWechatpubinfoid() {
		return wechatpubinfoid;
	}

	public void setWechatpubinfoid(Integer wechatpubinfoid) {
		this.wechatpubinfoid = wechatpubinfoid;
	}
	
}
