package weChat.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import weChat.domain.primary.MemberCache;

public interface MemberCacheRepository extends JpaRepository<MemberCache, String> {

	/**
	 * 查找会员信息
	 * @param companyID
	 * @param memberid
	 * @return
	 */
	public MemberCache findTopByCompanyIDAndMemberid(int companyID, String memberid);
}
