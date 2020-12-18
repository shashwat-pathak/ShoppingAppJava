/**
 * 
 */
package com.shashwat.mybasket.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Shashwat Pathak
 *
 */
public interface ProductServiceInterface {
	public ArrayList<String> getCategories() throws SQLException;

	public ArrayList<Map<String, Object>> getCategoryProducts(String category) throws SQLException;
}
