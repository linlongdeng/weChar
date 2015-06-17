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


}
