package weChat.parameter.impl;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import weChat.parameter.IReqParam;

public class MInfoReqParam  implements IReqParam{

	/**场所编号**/
	  @NotNull
	  @Size(max=5)
	private String companycode;
	  
	  @NotNull
	private String companypsw;
	  
	  @NotNull
	private int wechatpubinfoid;

	public String getCompanycode() {
		return companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}

	public String getCompanypsw() {
		return companypsw;
	}

	public void setCompanypsw(String companypsw) {
		this.companypsw = companypsw;
	}

	public int getWechatpubinfoid() {
		return wechatpubinfoid;
	}

	public void setWechatpubinfoid(int wechatpubinfoid) {
		this.wechatpubinfoid = wechatpubinfoid;
	} 
}
