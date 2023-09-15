package com.krakozhia.visa.configuration;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "VisaApplicationDataSourceConfiguration",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.krakozhia.visa.visaApplication"}
)
public class VisaApplicationDataSourceConfiguration {

    @Value("${visaApplication.db.url}")
    String dbUrl;

    @Value("${visaApplication.db.username}")
    String username;

    @Value("${visaApplication.db.passport}")
    String passport;

    public Map<String, String> jpaProperties() {
        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.hbm2ddl.auto", "none");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
        jpaProperties.put("javax.persistence.transactionType", "JTA");
        return jpaProperties;
    }

    @Bean(name = "visaApplicationEntityManagerFactoryBuilder")
    public EntityManagerFactoryBuilder visaApplicationEntityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(), jpaProperties(), null
        );
    }


    @Bean(name = "VisaApplicationDataSourceConfiguration")
    public LocalContainerEntityManagerFactoryBean getPostgresEntityManager(
            @Qualifier("visaApplicationEntityManagerFactoryBuilder") EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("visaApplicationDataSource") DataSource postgresDataSource
    ) {
        return entityManagerFactoryBuilder
                .dataSource(postgresDataSource)
                .packages("com.krakozhia.visa.visaApplication")
                .persistenceUnit("mysql")
                .properties(jpaProperties())
                .jta(true)
                .build();
    }

    @Bean("visaApplicationDataSourceProperties")
    public DataSourceProperties visaApplicationDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean("visaApplicationDataSource")
    public DataSource visaApplicationDataSource(@Qualifier("visaApplicationDataSourceProperties") DataSourceProperties dataSourceProperties) {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(dbUrl);
        mysqlXaDataSource.setUser(username);
        mysqlXaDataSource.setPassword(passport);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("xa_visaApplication");
        xaDataSource.setMaxPoolSize(30);
        return xaDataSource;
    }

}