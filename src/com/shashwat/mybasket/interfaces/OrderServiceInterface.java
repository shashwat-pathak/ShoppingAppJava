/**
 * 
 */
package com.shashwat.mybasket.interfaces;

import java.sql.SQLException;

/**
 * @author Shashwat Pathak
 *
 */
public interface OrderServiceInterface {
	public void displayOrders(String userId) throws SQLException;
}
