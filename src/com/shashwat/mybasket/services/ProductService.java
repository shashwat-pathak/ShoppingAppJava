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
public class ProductService {
	public ArrayList<String> getCategories() throws SQLException {
		ArrayList categories = new ArrayList<String>();
		String query = "Select distinct category from products";
		ArrayList<Map<String, Object>> productQuery = DBUtilities.getDataFromDB(query);
		for (Map<String, Object> map : productQuery) {
			categories.add(map.get("CATEGORY"));
		}
		return categories;
	}

	public ArrayList<Map<String, Object>> getCategoryProducts(String category) throws SQLException {
		ArrayList<Map<String, Object>> products = new ArrayList<Map<String, Object>>();
		String query = "Select * from products where category = '" + category + "'";
		products = DBUtilities.getDataFromDB(query);
		return products;
	}
}
