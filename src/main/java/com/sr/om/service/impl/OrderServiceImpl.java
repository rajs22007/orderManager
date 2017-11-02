package com.sr.om.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sr.om.dal.OrderRepository;
import com.sr.om.dal.mysql.model.Order;
import com.sr.om.service.OrderService;

@Service(value="orderService")
public class OrderServiceImpl implements OrderService {

	private static final String CREATED_STATE = "Created";
	
	@Inject
	private OrderRepository orderRepository;
	
	@Inject
	OrderUpdateDelegate orderUpdateDelegate;
	
	ProcessEngineConfiguration processEngineConfiguration  = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
	
	ProcessEngine processEngine = processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE).setJdbcUrl("jdbc:mysql://127.0.0.1:3306/sr_data?characterEncoding=UTF-8").setJdbcDriver("com.mysql.jdbc.Driver").setJdbcUsername("root").setJdbcPassword("root").buildProcessEngine();
	
	//@Autowired
    private RepositoryService repositoryService = processEngine.getRepositoryService();

//	@Inject
	private RuntimeService runtimeService = processEngine.getRuntimeService();
	
	private TaskService taskService = processEngine.getTaskService();
	
	

	/*private OrderRepository orderEntityRepository;
	private RuntimeService runtimeService;*/

	/*public OrderServiceImpl(final OrderRepository entityRepository, final RuntimeService runtimeService) {
		this.orderEntityRepository = checkNotNull(entityRepository);
		this.runtimeService = checkNotNull(runtimeService);
	}*/

	public Map<String, String> createOrder(Order orderEntity) throws FileNotFoundException {
		DeploymentBuilder deploymentBuilder  = repositoryService.createDeployment().addInputStream("NewOrderProcess.bpmn", new FileInputStream("D:/Projects/iAppraisal/sample-master/orderManager-master/src/main/resources/processes/NewOrderProcess.bpmn"));
		Deployment deployment = deploymentBuilder.deploy();
		System.out.println(deployment.getName());
		System.out.println("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());
		System.out.println("Create OrderEntity request: " + orderEntity.toString());
		orderEntity.setStatuCode(CREATED_STATE);
		Order savedOrderEntity = orderRepository.insertAndFlushReturnObj(orderEntity);
		System.out.println(savedOrderEntity.toString());
		if (StringUtils.isNotEmpty(orderEntity.getVendorUid()))
			return startProcess(savedOrderEntity.getId(), orderEntity.getVendorUid());

		Map<String, String> data = new HashMap<>();
		data.put("orderId", savedOrderEntity.getId().toString());
		data.put("statusCode", orderEntity.getStatuCode());
		data.put("message", "Order successfully created.");
		return data;
	}

	public Map<String, String> startProcess(Long orderId, String vendorUid) {
		Order orderEntity = orderRepository.findById(orderId);
		Map<String, String> data = new HashMap<>();
		data.put("orderId", orderId.toString());
		String message;

		if (null == orderEntity) {
			message = "Order not found.";
			data.put("message", message);
			return data;
		}

		if (!CREATED_STATE.equals(orderEntity.getStatuCode())) {
			message = "Couldn't start process as order isn't in Created state.";
			data.put("message", message);
			return data;
		}

		Map<String, Object> vars = new HashMap<>();
		vars.put("orderEntity", orderEntity);
		vars.put("orderId", orderId);
		vars.put("orderUpdateDelegate", orderUpdateDelegate);
		ProcessInstance instance = runtimeService.startProcessInstanceByKey("NewOrderProcess", vars);
		//instance.getProcessVariables().put("orderId", orderId);
		//instance.getProcessVariables().put("orderEntity", orderEntity);

		System.out.println("Process started...");
		System.out.println("Process details: " + instance.toString());
		System.out.println("Process Variables: " + instance.getProcessVariables().toString());

		if (StringUtils.isNotEmpty(vendorUid)) {
			orderEntity.setVendorUid(vendorUid);
			orderEntity.setStatuCode("Assigned");
			message = "Order process initiated! Assigned to " + vendorUid;
		} else {
			orderEntity.setStatuCode("Manual Assignment");
			message = "Order process initiated! Manual assignment to vendor is required.";
		}
		orderEntity.setOrderId(orderId);
		orderRepository.insertAndFlush(orderEntity);

		data.put("statusCode", orderEntity.getStatuCode());
		data.put("message", message);
		return data;
	}

	public Map<String, Object> searchOrder(String orderId) {
		Order orderEntity = orderRepository.findById(Long.valueOf(orderId));
		String message;
		Map<String, Object> data = new HashMap<>();
		data.put("orderEntity", orderEntity);

		message = (null == orderEntity) ? "Order not found." : "Matching order found.";
		data.put("message", message);
		return data;
	}

	public void updateVendorDetails() {
		System.out.println("In porgress...");
	}

	public void orderCompletedGreetings() {
		System.out.println("Order has been succufully completed...");
	}

	@Override
	public String getTasksById(Long orderId) {
		List<Task> tasks = fetchTaskQuery().processVariableValueEquals(orderId).orderByDueDateNullsFirst().asc().list();
		
		for(Task task: tasks){
			System.out.println("taskId: " + task.getId() + ", taskName: " + task.getName() + ", assignee: " + task.getAssignee() + "task variables: " + task.getProcessVariables());
			return task.toString();
		}
		
		return tasks.toString();
	}

	@Override
	public String getTasks() {
		List<Task> tasks = fetchTaskQuery().orderByDueDateNullsFirst().asc().list();
		for(Task task: tasks){
			System.out.println("taskId: " + task.getId() + ", taskName: " + task.getName() + ", assignee: " + task.getProcessVariables() + task.getTaskLocalVariables());
		}
		return tasks.toString();
	}
	
	@Override
	public String getTasksByAssignee(String assignee) {
		List<Task> tasks = fetchTaskQuery().taskAssignee(assignee).orderByDueDateNullsFirst().asc().list();
		for(Task task: tasks){
			System.out.println("taskId: " + task.getId() + ", taskName: " + task.getName() + ", assignee: " + task.getProcessVariables() + task.getTaskLocalVariables());
		}
		return tasks.toString();
	}

	@Override
	public String getTasksWithNoAssignee() {
		List<Task> tasks = fetchTaskQuery().taskUnassigned().orderByDueDateNullsFirst().asc().list();
		for(Task task: tasks){
			System.out.println("taskId: " + task.getId() + ", taskName: " + task.getName() + ", assignee: " + task.getProcessVariables() + task.getTaskLocalVariables());
		}
		return tasks.toString();
	}

	private TaskQuery fetchTaskQuery() {
		return taskService.createTaskQuery().includeTaskLocalVariables().includeProcessVariables();
	}
}
