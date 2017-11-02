package com.sr.om.config;

import javax.inject.Inject;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.springframework.context.annotation.Bean;

import com.sr.om.dal.OrderRepository;
import com.sr.om.service.OrderService;
import com.sr.om.service.impl.OrderServiceImpl;

public class ServiceConfig {

	//@Inject
	private OrderRepository orderRepository;

	//@Inject
	private RuntimeService runtimeService;
	
	//@Inject
	private RepositoryService repositoryService;
	
	//@Bean
	/*public OrderService orderService() {
		System.out.println("repositoryService: " + repositoryService);
		System.out.println("repositoryService.createDeployment: " + repositoryService.createDeployment());
		if(repositoryService!=null) repositoryService.createDeployment().addClasspathResource("processes/NewOrderProcess.bpmn").deploy();
		return new OrderServiceImpl(orderRepository, runtimeService);
	}*/

}
