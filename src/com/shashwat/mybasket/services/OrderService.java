/**
 * 
 */
package com.shashwat.mybasket.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.shashwat.mybasket.DBOperations.DBUtilities;
import com.shashwat.mybasket.interfaces.LoginServiceInterface;
import com.shashwat.mybasket.interfaces.OrderServiceInterface;

/**
 * @author Shashwat Pathak
 *
 */
public class OrderService implements OrderServiceInterface {
	public void displayOrders(String userId) throws SQLException {
		ArrayList<Map<String, Object>> orders = DBUtilities
				.getDataFromDB("Select * from orders where userId = '" + userId + "'");
		if (orders.size() > 0) {
			System.out.println(" **** Order List ****");
			for (Map<String, Object> map : orders) {
				System.out.println("OrderID: " + map.get("ORDERID") + " Date: " + map.get("ORDERDATE") + " Total: "
						+ map.get("TOTAL"));
			}
		} else {
			System.out.println("No Orders Found..");
		}

	}
}
