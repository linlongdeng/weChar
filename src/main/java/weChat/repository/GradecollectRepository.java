package weChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import weChat.domain.Gradecollect;

public interface GradecollectRepository extends
		JpaRepository<Gradecollect, Integer> {

	@Query("SELECT g FROM Gradecollect g, Company c WHERE g.companyID = c.companyID "
			+ "AND c.companyCode =?1 AND g.wechatPubInfoID =?2 AND g.gradeID =?3 limit 1")
	public Gradecollect findFirstByCompanyCodeAndWechatPubInfoIDAndGradeID(
			String companyCode, int wechatPubInfoID, int gradeID);

}
