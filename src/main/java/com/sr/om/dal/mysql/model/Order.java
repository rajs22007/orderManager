package com.sr.om.dal.mysql.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "ORDER_ENTITY")
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "ORDER_ID")
	private Long orderId;

	@Column(name = "ORDER_NAME")
	private String orderName;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "CLIENT_UID")
	private String clientUid;

	@Column(name = "VENDOR_UID")
	private String vendorUid;

	@Column(name = "VENDOR_USER_NAME")
	private String vendorUserName;

	@Column(name = "STATUS_CODE")
	private String statuCode;

	public Order() {
		super();
	}

	public Order(String orderName, String productName, String createdBy, String clientUid, String vendorUid) {
		super();
		this.orderName = orderName;
		this.productName = productName;
		this.createdBy = createdBy;
		this.clientUid = clientUid;
		this.vendorUid = vendorUid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getClientUid() {
		return clientUid;
	}

	public void setClientUid(String clientUid) {
		this.clientUid = clientUid;
	}

	public String getVendorUid() {
		return vendorUid;
	}

	public void setVendorUid(String vendorUid) {
		this.vendorUid = vendorUid;
	}

	public String getVendorUserName() {
		return vendorUserName;
	}

	public void setVendorUserName(String vendorUserName) {
		this.vendorUserName = vendorUserName;
	}

	public String getStatuCode() {
		return statuCode;
	}

	public void setStatuCode(String statuCode) {
		this.statuCode = statuCode;
	}

	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", orderId=" + orderId + ", orderName=" + orderName + ", productName="
				+ productName + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", clientUid=" + clientUid
				+ ", vendorUid=" + vendorUid + ", vendorUserName=" + vendorUserName + ", statuCode=" + statuCode + "]";
	}

}
