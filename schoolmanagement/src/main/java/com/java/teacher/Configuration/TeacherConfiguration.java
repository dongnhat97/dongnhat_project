package com.java.teacher.Configuration;


import com.java.teacher.entity.Teacher;
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

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "teacherDSEmFactory",
        transactionManagerRef = "teacherDSTransactionManager",
        basePackages = "com.java.teacher.repository"
)
public class TeacherConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource2")
    public DataSourceProperties teacherDSProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource teacherDS(@Qualifier("teacherDSProperties") DataSourceProperties worldDSProperties){
        return worldDSProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean teacherDSEmFactory(
            @Qualifier("teacherDS") DataSource teacherDS,
            EntityManagerFactoryBuilder builder
            ){
        return builder.dataSource(teacherDS).packages(Teacher.class).build();
    }

    @Bean
    public PlatformTransactionManager teacherDSTransactionManager(@Qualifier("teacherDSEmFactory")EntityManagerFactory teacherDSEmFactory){
        return new JpaTransactionManager(teacherDSEmFactory);
    }
}
