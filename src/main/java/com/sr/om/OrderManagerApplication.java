package com.sr.om;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration(exclude = {org.activiti.spring.boot.RestApiAutoConfiguration.class, 
		org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class, org.activiti.spring.boot.SecurityAutoConfiguration.class })
@SpringBootApplication
public class OrderManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagerApplication.class, args);
		System.out.println("OrderManagerApplication is started!");
	}

	/*
	 * @Bean public DataSource database() { return DataSourceBuilder.create()
	 * .url(
	 * "jdbc:mysql://127.0.0.1:3306/activiti-spring-boot?characterEncoding=UTF-8")
	 * .username("root") .password("root")
	 * .driverClassName("com.mysql.jdbc.Driver") .build(); }
	 */

	@Bean
	InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {

		return new InitializingBean() {
			public void afterPropertiesSet() throws Exception {

				Group group = identityService.newGroup("user");
				group.setName("users");
				group.setType("security-role");
				identityService.saveGroup(group);

				User admin = identityService.newUser("admin");
				admin.setPassword("admin");
				identityService.saveUser(admin);
			}
		};
	}
}
