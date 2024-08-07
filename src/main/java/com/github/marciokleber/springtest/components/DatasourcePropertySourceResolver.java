package com.github.marciokleber.springtest.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertySourceResolver {

    @Value("${example.firstProperty}") private String datasourceUrl;
    @Value("${example.secondProperty}") private String datasourceUsername;
    @Value("${example.secondProperty}") private String datasourcePassowrd;

    public String getFirstProperty() {
        return firstProperty;
    }

    public String getSecondProperty() {
        return secondProperty;
    }
}