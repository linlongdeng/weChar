package weChat.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wj_tbl_wechatpubinfo database table.
 * 
 */
@Entity
@Table(name="wj_tbl_wechatpubinfo")
@NamedQuery(name="Wechatpubinfo.findAll", query="SELECT w FROM Wechatpubinfo w")
public class Wechatpubinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int wechatPubInfoID;

	private String appID;

	private String appSecret;

	private String wechatPubID;

	private String wechatPubName;

	public Wechatpubinfo() {
	}

	public int getWechatPubInfoID() {
		return this.wechatPubInfoID;
	}

	public void setWechatPubInfoID(int wechatPubInfoID) {
		this.wechatPubInfoID = wechatPubInfoID;
	}

	public String getAppID() {
		return this.appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getAppSecret() {
		return this.appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getWechatPubID() {
		return this.wechatPubID;
	}

	public void setWechatPubID(String wechatPubID) {
		this.wechatPubID = wechatPubID;
	}

	public String getWechatPubName() {
		return this.wechatPubName;
	}

	public void setWechatPubName(String wechatPubName) {
		this.wechatPubName = wechatPubName;
	}

}