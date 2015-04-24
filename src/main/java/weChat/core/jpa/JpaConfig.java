package weChat.core.jpa;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
public class JpaConfig {
	@Autowired
	private Environment environment;
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Bean
	public String test(){
		//NamingStrategyDelegator
		//ImprovedNamingStrategyDelegator 
		//NamingStrategy 
		return "测试";
	}
}
