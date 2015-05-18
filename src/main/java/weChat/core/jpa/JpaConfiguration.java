package weChat.core.jpa;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ConfigurationProperties(prefix = JpaConfiguration.PRIMARY_PREFIX)
public class JpaConfiguration {

	public static final String PRIMARY_PREFIX = "wechat.primary";
	/** The name of the persistence unit. */
	public static final String PRIMARY_PERSISTENT_UNIT = "PRIMARY_PERSISTENT_UNIT";

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JpaProperties properties;

	private List<String> entityPackage = new ArrayList<String>();

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			EntityManagerFactoryBuilder builder) {
		Map<String, Object> vendorProperties = getVendorProperties();
		return builder.dataSource(dataSource)
				.packages(entityPackage.toArray(new String[0]))
				.properties(vendorProperties)
				.persistenceUnit(PRIMARY_PERSISTENT_UNIT).build();
	}

	protected Map<String, Object> getVendorProperties() {
		Map<String, Object> vendorProperties = new LinkedHashMap<String, Object>();
		vendorProperties.putAll(this.properties
				.getHibernateProperties(this.dataSource));
		return vendorProperties;
	}

	@Bean
	@Primary
	public EntityManager entityManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		return entityManager;
	}

	@Bean
	@Primary
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager(
				entityManagerFactory);
		return transactionManager;
	}

	@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", value = { EnableJpaRepositoriesConfiguration.REPOSITORY_PACKAGE }, transactionManagerRef = "transactionManager")
	private static class EnableJpaRepositoriesConfiguration {
		public static final String REPOSITORY_PACKAGE="weChat.repository.primary"; 
	}

	public List<String> getEntityPackage() {
		return entityPackage;
	}

	public void setEntityPackage(List<String> entityPackage) {
		this.entityPackage = entityPackage;
	}



	/*
	 * @Resource(name = "secordDataSource") private DataSource secordDataSource;
	 * 
	 * @Bean(name = "secordEntityManagerFactory") public
	 * LocalContainerEntityManagerFactoryBean secordEntityManagerFactory(
	 * EntityManagerFactoryBuilder builder) { String packages =
	 * environment.getProperty("spring.datasource2.package"); Map<String,
	 * Object> vendorProperties = getVendorProperties(); return
	 * builder.dataSource(secordDataSource).packages(packages)
	 * .properties(vendorProperties)
	 * .persistenceUnit("secordEntityManager").build(); }
	 * 
	 * @Bean public EntityManager secordEntityManager(
	 * 
	 * @Qualifier("secordEntityManagerFactory") EntityManagerFactory
	 * entityManagerFactory) { EntityManager entityManager =
	 * entityManagerFactory.createEntityManager(); return entityManager; }
	 * 
	 * @Bean public PlatformTransactionManager secordTransactinManager(
	 * 
	 * @Qualifier("secordEntityManagerFactory") EntityManagerFactory
	 * entityManagerFactory) { JpaTransactionManager transactionManager = new
	 * JpaTransactionManager( entityManagerFactory); return transactionManager;
	 * }
	 * 
	 * @EnableJpaRepositories(entityManagerFactoryRef =
	 * "secordEntityManagerFactory", value = { "weChat.secordRepository" },
	 * transactionManagerRef = "secordTransactinManager") private static class
	 * EnableSecondJpaRepositoriesConfiguration {
	 * 
	 * }
	 */

}
