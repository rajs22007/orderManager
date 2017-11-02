package com.sr.om.dal.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sr.om.dal.OrderRepository;
import com.sr.om.dal.mysql.model.Order;

@Repository
public interface MysqlOrderRepository extends JpaRepository<Order, String>,OrderRepository{

}
