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
	private int gradeCollectID;

	private int cardPicID;

	private int companyID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	private String gradeCode;

	private int gradeID;

	private String gradeName;

	private String memberRights;

	private byte status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	private int wechatPubInfoID;

	public Gradecollect() {
	}

	public int getGradeCollectID() {
		return this.gradeCollectID;
	}

	public void setGradeCollectID(int gradeCollectID) {
		this.gradeCollectID = gradeCollectID;
	}

	public int getCardPicID() {
		return this.cardPicID;
	}

	public void setCardPicID(int cardPicID) {
		this.cardPicID = cardPicID;
	}

	public int getCompanyID() {
		return this.companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getGradeCode() {
		return this.gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public int getGradeID() {
		return this.gradeID;
	}

	public void setGradeID(int gradeID) {
		this.gradeID = gradeID;
	}

	public String getGradeName() {
		return this.gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getMemberRights() {
		return this.memberRights;
	}

	public void setMemberRights(String memberRights) {
		this.memberRights = memberRights;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getWechatPubInfoID() {
		return this.wechatPubInfoID;
	}

	public void setWechatPubInfoID(int wechatPubInfoID) {
		this.wechatPubInfoID = wechatPubInfoID;
	}

}