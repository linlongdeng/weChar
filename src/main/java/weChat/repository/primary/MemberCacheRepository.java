package weChat.repository.primary;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import weChat.core.metatype.BaseDto;
import weChat.domain.primary.MemberCache;

public interface MemberCacheRepository extends
		JpaRepository<MemberCache, String> {

	/**
	 * 查找会员信息
	 * 
	 * @param companyID
	 * @param memberid
	 * @return
	 */
	public MemberCache findTopByCompanyIDAndMemberid(int companyID,
			String memberid);

	/**
	 * 查找会员信息
	 * 
	 * @param cardnum
	 * @param status
	 * @param wechatPubInfoID
	 * @return
	 */
	public MemberCache findTopByCardnumAndStatusAndWechatPubInfoID(
			String cardnum, String status, int wechatPubInfoID);
	
	/**
	 * 根据卡号和商家ID查找会员信息
	 * @param cardnum
	 * @param companyID
	 * @return
	 */
	public MemberCache findTopByCardnumAndCompanyID(String cardnum, int companyID);
	
	/**根据电子会子卡ID查找会员信息
	 * @param kmid
	 * @return
	 */
	public MemberCache findTopByKmid(String kmid);
	
	/**
	 * 根据K米会员APP_ID批量获取会员信息
	 */
	public final String MEMBER_INFO_SQL= "SELECT mc.KMID, mc.CompanyID, mc.MemberName, mc.birthday, mc.sex, mc.PaperType, mc.PaperNumber, mc.Cardnum, mc.CreateCardTime,"
			+ " mc.GradeID, mc.`status`, mc.mobile, mc.UseLimitDate, mc.IntegralBalance, mc.AccountBalance, mc.AccountCash, mc.AccountPresent, mc.LastConsumeTime, mc.UpdateTime, "
			+ "g.GradeName, g.CardPicID FROM wj_tbl_kmbindcard AS kbc INNER JOIN wj_tbl_member_cache AS mc ON kbc.Kmid = mc.KMID AND kbc. STATUS = 1 AND mc. STATUS = '启用' AND kbc.CustomerID = ? LEFT JOIN wj_tbl_gradecollect "
			+ " AS g ON mc.GradeID = g.GradeID AND mc.CompanyID = g.CompanyID";


}
