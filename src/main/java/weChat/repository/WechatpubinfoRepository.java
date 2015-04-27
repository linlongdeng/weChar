package weChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import weChat.domain.Wechatpubinfo;

public interface WechatpubinfoRepository extends
		JpaRepository<Wechatpubinfo, Integer> {

}
