package com.github.marciokleber.springtest;

import com.github.marciokleber.springtest.components.DatasourcePropertySourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringTestApplication implements CommandLineRunner {

	@Autowired
	private DatasourcePropertySourceResolver dr;

	private static final Logger log = LoggerFactory.getLogger(SpringTestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringTestApplication.class, args);
	}

	@Override
	public void run(String... args) {
//		log.info("Iniciando o application...");
//        log.info("Datasource url: {}", dr.getUrl());
//        log.info("Datasource username: {}", dr.getUsername());
//        log.info("Datasource password: {}", dr.getPassowrd());
	}
}
