package com.mavericks.costmanager.services;

import java.util.List;

import com.mavericks.costmanager.entities.UserBalance;

/**
 * UserBalanceService is responsible to manage current balance for all available
 * Users
 * 
 * @author Sourav
 */
public interface UserBalanceService {

	/**
	 * Set newBalance of user with `name` This method can be used to set initial
	 * balance 0
	 * 
	 * @param name
	 * @param newBalance
	 * @return
	 */
	public void setUserBalance(String name, double newBalance);

	/**
	 * 
	 * Add amount to user `name`
	 * 
	 * @param name
	 * @param amount
	 * @return
	 */
	public void addToUser(String name, double amount);

	/**
	 * Update All user balance by `amount`
	 * 
	 * @param amount
	 * @return
	 */
	public void addToAll(double amount);

	/**
	 * Returns all user Current Balance
	 * 
	 * @return List<UserBalance>
	 */
	public List<UserBalance> getAllUserBalance();

}
