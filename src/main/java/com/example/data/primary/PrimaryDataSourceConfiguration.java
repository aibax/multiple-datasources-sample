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

@Configuration
//@formatter:off
@EnableJpaRepositories(
    basePackageClasses = { PrimaryDataSourceConfiguration.class },
    entityManagerFactoryRef = "primaryEntityManagerFactory",
    transactionManagerRef = "primaryTransactionManager")
//@formatter:on
public class PrimaryDataSourceConfiguration
{
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource dataSource()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder)
    {
        /* [REQUIRED] hibernate.ejb.naming_strategy の設定が必須 */
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.ejb.naming_strategy", SpringNamingStrategy.class.getName());

        //@formatter:off
        return builder.dataSource(dataSource())
            .packages(PrimaryDataSourceConfiguration.class.getPackage().getName())
            .persistenceUnit("primary")
            .properties(properties)
            .build();
        //@formatter:on
    }

    @Bean
    @Primary
    public PlatformTransactionManager primaryTransactionManager(EntityManagerFactoryBuilder builder)
    {
        return new JpaTransactionManager(primaryEntityManagerFactory(builder).getObject());
    }
}