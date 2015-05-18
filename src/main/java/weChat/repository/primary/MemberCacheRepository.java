package weChat.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import weChat.domain.primary.MemberCache;

public interface MemberCacheRepository extends JpaRepository<MemberCache, String> {

}
