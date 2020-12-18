/**
 * 
 */
package com.shashwat.mybasket.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.shashwat.mybasket.DBOperations.DBUtilities;
import com.shashwat.mybasket.interfaces.UserServiceInterface;
import com.shashwat.mybasket.models.User;

/**
 * @author Shashwat Pathak
 *
 */
public class UserService implements UserServiceInterface {
	public Boolean createUser(User user) throws SQLException {
		Boolean userCreated = false;
		String sqlQuery = "Insert into users values ('" + user.getUserId() + "', '" + user.getUserName() + "', '"
				+ user.getPassword() + "', '" + user.getPhoneNumber() + "', '" + user.getAddress() + "' )";
		int result = DBUtilities.insertData(sqlQuery);
		userCreated = result > 0 ? true : false;
		return userCreated;
	}

	public void searchUser(String username) throws SQLException {
		ArrayList<Map<String, Object>> queryResult = new ArrayList<Map<String, Object>>();
		String fetchUserDetailQuery = "Select * from users where username = '" + username + "'";
		queryResult = DBUtilities.getDataFromDB(fetchUserDetailQuery);
		if (queryResult.size() > 0) {
			System.out.println("~~ USER DETAILS ~~~");
			for (Map<String, Object> map : queryResult) {
				System.out.println("UserName: " + map.get("USERNAME"));
				System.out.println("Phone Number: " + map.get("PHONE_NUMBER"));
				System.out.println("Address: " + map.get("ADDRESS"));
			}

			ArrayList<Map<String, Object>> orderQueryResult = new ArrayList<Map<String, Object>>();
			String orderDetailsQuery = "select * from cart where userid = '" + queryResult.get(0).get("USERID")
					+ "' and CHECKED_OUT='Yes'";
			orderQueryResult = DBUtilities.getDataFromDB(orderDetailsQuery);
			if (orderQueryResult.size() > 0) {
				System.out.println("~~~ ORDER HISTORY ~~~");
				for (Map<String, Object> map : orderQueryResult) {
					System.out.println("Order ID: " + map.get("ORDERID") + ", Item: " + map.get("PRODUCT"));
				}
			}

		} else {
			System.out.println("User data not found!");
		}
	}
}
