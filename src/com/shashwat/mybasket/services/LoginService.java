/**
 * 
 */
package com.shashwat.mybasket.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.shashwat.mybasket.DBOperations.DBUtilities;
import com.shashwat.mybasket.models.User;

/**
 * @author Shashwat Pathak
 *
 */
public class LoginService {
	public ArrayList<Map<String, Object>> loginUser(User user) throws SQLException {
		Boolean loginStatus = false;
		String query = "Select * from users where username = '" + user.getUserName() + "' and password = '"
				+ user.getPassword() + "'";
		ArrayList<Map<String, Object>> result = DBUtilities.getDataFromDB(query);
		return result;
	}
}
