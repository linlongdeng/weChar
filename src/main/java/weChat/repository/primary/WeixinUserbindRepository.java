package weChat.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import weChat.domain.primary.WeixinUserbind;

public interface WeixinUserbindRepository extends JpaRepository<WeixinUserbind, Integer> {

	
	public WeixinUserbind findFirstByKmidAndStatus(String kmid, byte status);
	

}
