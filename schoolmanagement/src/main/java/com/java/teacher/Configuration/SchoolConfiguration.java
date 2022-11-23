package com.java.teacher.Configuration;

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

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "schoolDSEmFactory",
        transactionManagerRef = "schoolDSTransactionManager",
        basePackages = "com.java.common.repository"
)
public class SchoolConfiguration {
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource1")
    public DataSourceProperties schoolDSProperties(){
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource schoolDS(@Qualifier("schoolDSProperties") DataSourceProperties schoolDSProperties){
        return schoolDSProperties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean schoolDSEmFactory(@Qualifier("schoolDS") DataSource schoolDS, EntityManagerFactoryBuilder builder){
        return builder.dataSource ( schoolDS ).packages ("com.java.common.entity").build ();
    }

    @Primary
    @Bean
    public PlatformTransactionManager schoolDSTransactionManager(@Qualifier("schoolDSEmFactory") EntityManagerFactory schoolDSEmFactory){
        return new JpaTransactionManager ( schoolDSEmFactory );
    }
}
