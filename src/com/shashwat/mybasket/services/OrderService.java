/**
 * 
 */
package com.shashwat.mybasket.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.shashwat.mybasket.DBOperations.DBUtilities;

/**
 * @author Shashwat Pathak
 *
 */
public class OrderService {
	public void displayOrders(String userId) throws SQLException {
		ArrayList<Map<String, Object>> orders = DBUtilities
				.getDataFromDB("Select * from orders where userId = '" + userId + "'");
		if (orders.size() > 0) {
			System.out.println(" **** Order List ****");
			for (Map<String, Object> map : orders) {
				System.out.println("OrderID: " + map.get("ORDERID") + " Date: " + map.get("ORDERDATE"));
			}
		} else {
			System.out.println("No Orders Found..");
		}

	}
}
