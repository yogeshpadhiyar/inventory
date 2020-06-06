package com.neosoft.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class DatabaseConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Value("${db.driver}")
    private  String DRIVER;
    @Value("${db.username}")
    private  String USERNAME;
    @Value("${db.password}")
    private  String PASSWORD;
    @Value("${db.url}")
    private  String URI;

    @Value("${hibernate.url}")
    private  String HB_URI;
    @Value("${hibernate.dialect}")
    private String DIALECT;
    @Value("${hibernate.show.sql}")
    private String SHOW_SQL;
    @Value("${hibernate.hbm2ddl.auto}")
    private String HBM2DDL_AUTO;
    @Value("${entity.manager.packages.to.scan}")
    private String PACKAGES_TO_SCAN;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
            LOGGER.info("Create connection with Database.");
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URI , USERNAME ,PASSWORD);
            LOGGER.info("Connection Establish.");
            return connection;
    }

    @Bean
    public DataSource dataSource(){
        LOGGER.info("Creating DataSource with Database.");
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(DRIVER);
        driverManagerDataSource.setUsername(USERNAME);
        driverManagerDataSource.setPassword(PASSWORD);
        driverManagerDataSource.setUrl(HB_URI);
        return driverManagerDataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LOGGER.info("Creating sessionFactory with Database.");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect",DIALECT);
        hibernateProperties.put("hibernate.show.sql",SHOW_SQL);
        hibernateProperties.put("hibernate.hbm2ddl.auto",HBM2DDL_AUTO);
        sessionFactory.setHibernateProperties(hibernateProperties);
        LOGGER.info("Set hibernate properties with Database.");
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
