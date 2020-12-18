/**
 * 
 */
package com.shashwat.mybasket.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.shashwat.mybasket.DBOperations.DBUtilities;
import com.shashwat.mybasket.interfaces.CartServiceInterface;
import com.shashwat.mybasket.models.Cart;
import com.shashwat.mybasket.models.Order;

/**
 * @author Shashwat Pathak
 *
 */
public class CartService implements CartServiceInterface {
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
		String query = "Insert into orders values ('" + order.getOrderId() + "', sysdate, '" + order.getUserId() + "',"
				+ order.getTotal() + ")";
		int result = DBUtilities.insertData(query);
		if (result > 0) {
			status = true;
			// update the checkout status in the cart table as well
			String updateQuery = "update cart set CHECKED_OUT = 'Yes', ORDERID = '" + order.getOrderId()
					+ "' where USERID='" + order.getUserId() + "' and ORDERID is null";
			DBUtilities.insertData(updateQuery);
		}
		return status;
	}

	@Override
	public Boolean updateCart(Cart cart) throws SQLException {
		// TODO Auto-generated method stub
		Boolean cartUpdated = true;
		List<String> productList = cart.getProductList();
		for (String product : productList) {
			// if the current product is already in the db then skip
			if (getCartFromDB(cart.getUserId()).contains(product)) {
				continue;
			} else {
				// Initially the checkout status is No and order id is set to null
				String query = "Insert into cart values ('" + product + "', '" + cart.getUserId() + "', 'No', NULL)";
				int result = DBUtilities.insertData(query);
				cartUpdated = cartUpdated && (result != -1);
			}
		}
		return cartUpdated;
	}

	@Override
	public ArrayList<String> getCartFromDB(String userId) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<String> toReturn = new ArrayList<String>();
		String query = "Select product from cart where userid='" + userId + "' and checked_out='No'";
		ArrayList<Map<String, Object>> addedProducts = DBUtilities.getDataFromDB(query);
		for (Map<String, Object> map : addedProducts) {
			toReturn.add(map.get("PRODUCT") + "");
		}
		return toReturn;
	}
}
