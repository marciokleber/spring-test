package com.github.marciokleber.springtest.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatasourcePropertySourceResolver {

    @Value("${spring.datasource.url}") private String url;
    @Value("${spring.datasource.username}") private String username;
    @Value("${spring.datasource.password}") private String passowrd;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassowrd() {
        return passowrd;
    }

}