package com.neosoft.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/hbqueries.properties")
public class QueryProperty {

    private final Environment environment;

    @Autowired
    public QueryProperty(Environment environment) {
        this.environment=environment;
    }


    public String query(String queryName){
         return environment.getProperty(queryName);
    }
}
