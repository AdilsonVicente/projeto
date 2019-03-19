package analiseskillus.com.br.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import analiseskillus.com.br.model.Usuario;
import analiseskillus.com.br.repository.Usuarios;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories( basePackageClasses=Usuarios.class , 
                        entityManagerFactoryRef="primaryDBEmFactory" , 
                        transactionManagerRef = "primaryDSTransactionManager")
public class PrimayDbConfig {

	@Primary
	@Bean
	@ConfigurationProperties("spring.datasource")
    public DataSourceProperties primaryDSProperties() {
        return new DataSourceProperties();
           
    }
    
     @Primary
     @Bean
	  public DataSource primaryDataSource(@Qualifier("primaryDSProperties") DataSourceProperties primaryDSProperties) {
	       return primaryDSProperties.initializeDataSourceBuilder().build();
	}

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean primaryDBEmFactory(@Qualifier("primaryDataSource") DataSource primaryDataSource, EntityManagerFactoryBuilder builder) {
		return builder.dataSource(primaryDataSource).packages(Usuario.class).persistenceUnit("usuarios").build();
	}
	
	@Primary
	@Bean
	public PlatformTransactionManager primaryDSTransactionManager(EntityManagerFactory factory) {
	     return new JpaTransactionManager(factory);
	}
}