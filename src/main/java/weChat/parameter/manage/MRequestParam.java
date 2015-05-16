package weChat.parameter.manage;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import weChat.core.metatype.BaseDto;
/**
 * 管理系统数据同步接口（管理系统发起）送上来的参数
 * @author deng
 * @date 2015年4月23日
 * @version 1.0.0
 */
public class MRequestParam {

	/**场所编号**/
	  @NotNull
	  @Size(max=5)
	  //@Pattern(regexp = "^(1234)|(456)$",message="商家编码格式不正确")
	private String companycode;
	/**场所密码**/
	  @NotNull
	private String companypsw;
	
	/**场所服务号ID**/
	  @NotNull
	private String WechatPubInfoID;
	/**数据**/
	private List<BaseDto> data;

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

	public String getWechatPubInfoID() {
		return WechatPubInfoID;
	}

	public void setWechatPubInfoID(String wechatPubInfoID) {
		WechatPubInfoID = wechatPubInfoID;
	}

	public List<BaseDto> getData() {
		return data;
	}

	public void setData(List<BaseDto> data) {
		this.data = data;
	}


}
