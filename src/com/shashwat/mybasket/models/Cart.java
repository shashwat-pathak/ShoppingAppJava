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

	public ArrayList<String> getProductList() {
		return productList;
	}

	public void setProductList(String product) {
		this.productList.add(product);
	}

}
