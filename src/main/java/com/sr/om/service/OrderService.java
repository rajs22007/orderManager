package com.sr.om.service;

import java.io.FileNotFoundException;
import java.util.Map;

import com.sr.om.dal.mysql.model.Order;

//@Service
public interface OrderService {

	public Map<String, String> createOrder(Order orderEntity) throws FileNotFoundException;
	public Map<String, String> startProcess(Long orderId, String vendorUid);
	public Map<String, Object> searchOrder(String orderId);
	public String getTasks();
	public String getTasksById(Long orderId);
	public String getTasksByAssignee(String assignee);
	public String getTasksWithNoAssignee();
}
