package weChat.core.jpa;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JpaConfiguration {

	@Autowired
	private DataSource primaryDataSource;

	@Resource(name = "secordDataSource")
	private DataSource secordDataSource;

	@Autowired
	private Environment environment;

	@Autowired
	private JpaProperties properties;

	@Bean(name = "entityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
			EntityManagerFactoryBuilder builder) {
		String packages = environment.getProperty("spring.datasource.package");
		Map<String, Object> vendorProperties = getVendorProperties();
		return builder.dataSource(primaryDataSource).packages(packages)
				.properties(vendorProperties)
				.persistenceUnit("primaryEntityManager").build();
	}

	@Bean(name = "secordEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean secordEntityManagerFactory(
			EntityManagerFactoryBuilder builder) {
		String packages = environment.getProperty("spring.datasource2.package");
		Map<String, Object> vendorProperties = getVendorProperties();
		return builder.dataSource(secordDataSource).packages(packages)
				.properties(vendorProperties)
				.persistenceUnit("secordEntityManager").build();
	}

	protected Map<String, Object> getVendorProperties() {
		Map<String, Object> vendorProperties = new LinkedHashMap<String, Object>();
		vendorProperties.putAll(this.properties
				.getHibernateProperties(this.primaryDataSource));
		return vendorProperties;
	}

	@Bean
	@Primary
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager(
				entityManagerFactory);
		return transactionManager;
	}

	@Bean
	public PlatformTransactionManager secordTransactinManager(
			@Qualifier("secordEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager(
				entityManagerFactory);
		return transactionManager;
	}

/*	@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", 
			value = { "weChat.repository" }, transactionManagerRef = "transactionManager")
	private static class EnableJpaRepositoriesConfiguration {
	}
	@EnableJpaRepositories(entityManagerFactoryRef = "secordEntityManagerFactory", 
			value = { "weChat.secordRepository" }, transactionManagerRef = "secordEntityManagerFactory")
	private static class  EnableSecondJpaRepositoriesConfiguration{
		
	}*/

}
