package com.example.data.primary;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration(value = "primaryDataSourceConfiguration")
//@formatter:off
@EnableJpaRepositories(
    basePackageClasses = { DataSourceConfiguration.class },
    entityManagerFactoryRef = "primaryEntityManagerFactory",
    transactionManagerRef = "primaryTransactionManager")
//@formatter:on
public class DataSourceConfiguration
{
    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource dataSource()
    {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder)
    {
        /* [REQUIRED] hibernate.ejb.naming_strategy の設定が必須 */
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.ejb.naming_strategy", SpringNamingStrategy.class.getName());

        //@formatter:off
        return builder.dataSource(dataSource())
            .packages(DataSourceConfiguration.class.getPackage().getName())
            .persistenceUnit("primary")
            .properties(properties)
            .build();
        //@formatter:on
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder)
    {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }
}