package br.com.analiseskillus.relatorioskillusportal.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.analiseskillus.relatorioskillusportal.model.RelatorioPortal;
import br.com.analiseskillus.relatorioskillusportal.repository.RelatoriosPortal;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses=RelatoriosPortal.class , 
						entityManagerFactoryRef="mysqlDBEmFactory" , 
						transactionManagerRef = "mysqlDSTransactionManager")
public class SecondDbConfig {
 
  
 @Bean
 @ConfigurationProperties("spring.datasourcebanco3")
 public DataSourceProperties mysqlDSProperties() {
       return new DataSourceProperties();
                
 }
 
  @Bean
  public DataSource mysqlDataSource(@Qualifier("mysqlDSProperties") DataSourceProperties mysqlDSProperties) {
        DataSource dataSource = mysqlDSProperties.initializeDataSourceBuilder().build();
        return dataSource;
       }
       
 @Bean
 public LocalContainerEntityManagerFactoryBean mysqlDBEmFactory(@Qualifier("mysqlDataSource") DataSource mysqlDataSource, EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean factory = builder.dataSource(mysqlDataSource).packages(RelatorioPortal.class).build();
        factory.setPersistenceUnitName("mysql");
        return factory; 
   }
   
    
 @Bean
 public PlatformTransactionManager mysqlDSTransactionManager(EntityManagerFactory factory) {
         return new JpaTransactionManager(factory);
 }
    
}

