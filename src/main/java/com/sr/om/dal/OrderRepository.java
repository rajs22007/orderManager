package com.sr.om.dal;

import com.sr.om.dal.mysql.model.Order;

public interface OrderRepository {
	void insertAndFlush(Order order);
	Order insertAndFlushReturnObj(Order order);
	
	Order findById(Long id);
}
