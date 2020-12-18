/**
 * 
 */
package com.shashwat.mybasket.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.shashwat.mybasket.models.Cart;
import com.shashwat.mybasket.models.Order;

/**
 * @author Shashwat Pathak
 *
 */
public interface CartServiceInterface {
	public int getCartTotalAmount(ArrayList<String> items) throws SQLException;
	public Boolean checkout(Order order) throws SQLException;
	public Boolean updateCart(Cart cart) throws SQLException;
	public ArrayList<String> getCartFromDB(String userId) throws SQLException;
}
