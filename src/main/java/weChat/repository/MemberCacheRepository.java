package weChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import weChat.domain.MemberCache;

public interface MemberCacheRepository extends JpaRepository<MemberCache, String> {

}
