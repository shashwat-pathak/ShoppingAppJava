/**
 * 
 */
package com.shashwat.mybasket.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.shashwat.mybasket.models.User;

/**
 * @author Shashwat Pathak
 *
 */
public interface LoginServiceInterface {
	public ArrayList<Map<String, Object>> loginUser(User user) throws SQLException;
}
