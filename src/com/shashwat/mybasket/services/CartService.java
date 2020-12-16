/**
 * 
 */
package com.shashwat.mybasket.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.shashwat.mybasket.DBOperations.DBUtilities;
import com.shashwat.mybasket.models.Order;

/**
 * @author Shashwat Pathak
 *
 */
public class CartService {
	public int getCartTotalAmount(ArrayList<String> items) throws SQLException {
		int total = 0;
		String itemList = "";
		for (String item : items) {
			itemList += "'" + item + "',";
		}
		itemList = itemList.substring(0, itemList.length() - 1);
		String query = "Select SUM(PRICE) as PRICE from products where PRODUCTNAME in (" + itemList + ")";
		ArrayList<Map<String, Object>> result = DBUtilities.getDataFromDB(query);
		total = Integer.parseInt(result.get(0).get("PRICE") + "");
		return total;
	}

	public Boolean checkout(Order order) throws SQLException {
		Boolean status = false;
		String query = "Insert into orders values ('" + order.getOrderId() + "', sysdate, '" + order.getUserId() + "')";
		int result = DBUtilities.insertData(query);
		if (result > 0) {
			status = true;
		}
		return status;
	}
}
