/**
 * 
 */
package com.shashwat.mybasket.services;

import java.sql.SQLException;

import com.shashwat.mybasket.DBOperations.DBUtilities;
import com.shashwat.mybasket.models.User;

/**
 * @author Shashwat Pathak
 *
 */
public class UserService {
	public Boolean createUser(User user) throws SQLException {
		Boolean userCreated = false;
		String sqlQuery = "Insert into users values ('" + user.getUserId() + "', '" + user.getUserName() + "', '"
				+ user.getPassword() + "', '" + user.getPhoneNumber() + "', '" + user.getAddress() + "' )";
		int result = DBUtilities.insertData(sqlQuery);
		userCreated = result > 0 ? true : false;
		return userCreated;
	}
}
