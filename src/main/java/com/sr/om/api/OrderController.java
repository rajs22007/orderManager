package com.sr.om.api;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sr.om.dal.mysql.model.Order;
import com.sr.om.service.OrderService;

@RestController
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	public OrderController(final OrderService orderService) {
		this.orderService = checkNotNull(orderService);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> createOrder(@RequestHeader String uid, @RequestBody Map<String, String> data)
			throws FileNotFoundException {
		LOGGER.info("Order creation request data: {}", data);
		Order orderEntity = new Order(data.get("orderName"), data.get("productName"), data.get("createdBy"),
				data.get("clientUid"), data.get("vendorUid"));
		Map<String, String> orderCreationResult = orderService.createOrder(orderEntity);
		LOGGER.info("Order creation response data: {}", orderCreationResult);
		return orderCreationResult;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/start-order-process", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> startOrderProcess(@RequestHeader String uid, @RequestBody Map<String, String> data) {
		Long orderId = Long.valueOf(data.get("orderId"));
		String vendorUid = data.get("vendorUid");
		Map<String, String> startProcessResult = orderService.startProcess(orderId, vendorUid);
		LOGGER.info("Start Process response for orderId {} is: {}", orderId, startProcessResult);
		return startProcessResult;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/order/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> searchOrder(@PathVariable String id, @RequestHeader String uid) {
		LOGGER.info("Order Search Request arrived for orderId: {} by user: {}", id, uid);
		Map<String, Object> data = orderService.searchOrder(id);
		LOGGER.info("Order Search Response for orderId: {} by user: {} is: {}", id, uid, data);
		return data;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getTasks(@RequestHeader String uid) {
		LOGGER.info("Task Search Request arrived by user: {}", uid);
		String data = orderService.getTasks();
		LOGGER.info("Task Search Response for user: {} is: {}", uid, data);
		return Collections.singletonMap("data", data);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/order/{id}/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getTasksById(@PathVariable long id, @RequestHeader String uid) {
		LOGGER.info("Task Search Request arrived by user: {} for orderId: {}", uid, id);
		String data = orderService.getTasksById(id);
		LOGGER.info("Task Search Response for user: {}, orderId: {} is: {}", uid, id, data);
		return Collections.singletonMap("data", data);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/tasks/assigned", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getAssignedTasks(@RequestHeader String uid) {
		LOGGER.info("Task Search Request arrived with assignee: {}", uid);
		String data = orderService.getTasksByAssignee(uid);
		LOGGER.info("Task Search Response for assignee: {} is: {}", uid, data);
		return Collections.singletonMap("data", data);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/tasks/unassigned", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getUnassignedTasks(@RequestHeader String uid) {
		LOGGER.info("Task Search Request arrived by user: {} with no assignee!!", uid);
		String data = orderService.getTasksWithNoAssignee();
		LOGGER.info("Task Search Response for user: {} with no assignee is: {}", uid, data);
		return Collections.singletonMap("data", data);
	}

}
