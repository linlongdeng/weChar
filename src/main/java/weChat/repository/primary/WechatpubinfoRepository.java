package weChat.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import weChat.domain.primary.Wechatpubinfo;

public interface WechatpubinfoRepository extends
		JpaRepository<Wechatpubinfo, Integer> {

}
