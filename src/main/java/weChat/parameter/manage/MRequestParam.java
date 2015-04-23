package weChat.parameter.manage;

import java.util.List;
import java.util.Map;
/**
 * 管理系统数据同步接口（管理系统发起）送上来的参数
 * @author deng
 * @date 2015年4月23日
 * @version 1.0.0
 */
public class MRequestParam {

	/**场所编号**/
	private String companycode;
	/**场所密码**/
	private String companypsw;
	
	/**场所服务号ID**/
	private String WechatPubInfoID;
	/**数据**/
	private List<Map<String, Object>> data;

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

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}
}
