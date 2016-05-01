package com.example.data.secondary;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//@formatter:off
@EnableJpaRepositories(
    basePackageClasses = { SecondaryDataSourceConfiguration.class },
    entityManagerFactoryRef = "secondaryEntityManagerFactory",
    transactionManagerRef = "secondaryTransactionManager")
//@formatter:on
public class SecondaryDataSourceConfiguration
{
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder)
    {
        /* [REQUIRED] hibernate.ejb.naming_strategy の設定が必須 */
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.ejb.naming_strategy", SpringNamingStrategy.class.getName());

        //@formatter:off
        return builder.dataSource(secondaryDataSource())
            .packages(SecondaryDataSourceConfiguration.class.getPackage().getName())
            .persistenceUnit("secondary")
            .properties(properties)
            .build();
        //@formatter:on
    }

    @Bean
    public PlatformTransactionManager secondaryTransactionManager(EntityManagerFactoryBuilder builder)
    {
        return new JpaTransactionManager(secondaryEntityManagerFactory(builder).getObject());
    }
}