package br.com.hoptech.btclean.presenter.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"br.com.hoptech.btclean.data.db.jpa.entities"})
@EnableJpaRepositories(basePackages = {"br.com.hoptech.btclean.data.db.jpa.repositories"})
public class DBConfig {
}
