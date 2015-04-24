package weChat.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the wj_tbl_gradecollect database table.
 * 
 */
@Entity
@Table(name="wj_tbl_gradecollect")
@NamedQuery(name="Gradecollect.findAll", query="SELECT g FROM Gradecollect g")
public class Gradecollect implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int gradecollectid;

	private int cardpicid;

	@Column(name="companyID")
	private int companyid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createtime;

	private String gradecode;

	private int gradeid;

	private String gradename;

	private String memberrights;

	private byte status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatetime;

	private int wechatpubinfoid;

	public Gradecollect() {
	}

	public int getGradecollectid() {
		return gradecollectid;
	}

	public void setGradecollectid(int gradecollectid) {
		this.gradecollectid = gradecollectid;
	}

	public int getCardpicid() {
		return cardpicid;
	}

	public void setCardpicid(int cardpicid) {
		this.cardpicid = cardpicid;
	}

	public int getCompanyid() {
		return companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getGradecode() {
		return gradecode;
	}

	public void setGradecode(String gradecode) {
		this.gradecode = gradecode;
	}

	public int getGradeid() {
		return gradeid;
	}

	public void setGradeid(int gradeid) {
		this.gradeid = gradeid;
	}

	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public String getMemberrights() {
		return memberrights;
	}

	public void setMemberrights(String memberrights) {
		this.memberrights = memberrights;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public int getWechatpubinfoid() {
		return wechatpubinfoid;
	}

	public void setWechatpubinfoid(int wechatpubinfoid) {
		this.wechatpubinfoid = wechatpubinfoid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}