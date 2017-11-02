package com.sr.om.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.sr.om.api.OrderController;

//@Configuration
//@Import({ ServiceConfig.class })
//@ComponentScan({ "com.sr.om.api.mapper", "com.sr.om.service.mapper" })
public class ApiConfig {

	
	//@Inject
	private ServiceConfig serviceConfig;
	
	//@Bean
	/*public OrderController orderController()
	{
		return new OrderController(serviceConfig.orderService());
	}*/
	
	
	
}
