package com.sr.om.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@Service
public class OrderUpdateDelegate implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void updateVendorDetails() {
		System.out.println("In porgress...");
	}

	public void orderCompletedGreetings() {
		System.out.println("Order has been succufully completed...");
	}

}
