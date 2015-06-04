package weChat.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import weChat.domain.primary.Kmbindcard;

public interface KmbindcardRepository extends
		JpaRepository<Kmbindcard, Integer> {

	/**
	 * 查找K米APP绑卡关系
	 * 
	 * @param kmid
	 * @return
	 */
	public Kmbindcard findFirstByKmid(String kmid);
}
