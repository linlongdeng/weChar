package weChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import weChat.domain.Gradecollect;

public interface GradecollectRepository extends
		JpaRepository<Gradecollect, String> {

	public Gradecollect findFirstByCompanyIDAndWechatPubInfoIDAndGradeID(
			int companyID, int wechatPubInfoID, int gradeID);

}
