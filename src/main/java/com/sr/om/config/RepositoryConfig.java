package com.sr.om.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sr.om.dal.mysql.MarkerRepository;
import com.sr.om.dal.mysql.custom.InsertSupportedJpaRepositoryImpl;

@Configuration
@EnableJpaRepositories(basePackageClasses = MarkerRepository.class, repositoryBaseClass = InsertSupportedJpaRepositoryImpl.class)
@EntityScan(basePackages = "com.mp.fc.dal.mysql.model")
@Import({ DatabaseConfig.class, MultiDataSourceConfig.class })
public class RepositoryConfig {

}
