/**
 * 
 */
package com.shashwat.mybasket.models;

/**
 * @author Shashwat Pathak
 *
 */
public class Order {
	String orderId, date, userId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
