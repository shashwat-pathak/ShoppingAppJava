/**
 * 
 */
package com.shashwat.mybasket.models;

import java.util.ArrayList;

/**
 * @author Shashwat Pathak
 *
 */
public class Cart {
	ArrayList<String> productList = new ArrayList<String>();
	String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ArrayList<String> getProductList() {
		return productList;
	}

	public void setProductList(String product) {
		this.productList.add(product);
	}

	public void clearProductList() {
		productList.clear();
	}

}
