package weChat.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import weChat.domain.primary.Parameter;
/**
 * 参数Repository
 * @author deng
 * @date 2015年5月22日
 * @version 1.0.0
 */
public interface ParameterRepository extends JpaRepository<Parameter, Integer>{

	
	public Parameter findFirstByCompanyIDAndParameterName(int companyID,String parameterName);
}
