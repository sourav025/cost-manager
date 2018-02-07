package com.mavericks.costmanager.services;

/**
 * Processes All the user operations
 * 
 * @author Sourav
 *
 */
public interface UserService {

	/**
	 * @param name
	 * @return boolean
	 */
	public boolean isExists(String name);

	/**
	 * Register a new user
	 * 
	 * @param name
	 * @return boolean
	 */
	public boolean registerUser(String name);

	/**
	 * Calculates total number of Users
	 * 
	 * @return long
	 */
	public long getTotalUser();
}
