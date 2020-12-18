/**
 * 
 */
package com.shashwat.mybasket.interfaces;

import java.sql.SQLException;

import com.shashwat.mybasket.models.User;

/**
 * @author Shashwat Pathak
 *
 */
public interface UserServiceInterface {
	public Boolean createUser(User user) throws SQLException;
	public void searchUser(String username) throws SQLException;
}
