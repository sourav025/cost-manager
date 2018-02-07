package com.mavericks.costmanager.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.mavericks.costmanager.entities.ExpenseAddResultEntity;
import com.mavericks.costmanager.entities.ExpenseEntity;
import com.mavericks.costmanager.entities.Settlement;
import com.mavericks.costmanager.entities.UserAddResultEntity;

/**
 * 
 * Application accessible services Keeps track of adding new user Add New
 * expense Transaction Provides current status
 * 
 * @author Sourav
 */
public interface CostManagerService {
	/**
	 * Register a new User
	 * 
	 * @param user
	 * @return boolean
	 */
	public boolean addUser(String name);
	
	/**
	 * Add list users from file
	 * return total number of user registered
	 * @param usersFile
	 * @return
	 * @throws IOException 
	 */
	public UserAddResultEntity addUsers(File usersFile) throws IOException;

	/**
	 * Add new Expense Transaction On successfully addition, this method returns
	 * the headCost added for All Users
	 * 
	 * @param expenseEntity
	 * @return double
	 */
	public double addExpenseTransaction(ExpenseEntity expenseEntity);

	/**
	 * 
	 * ExpenseString from file
	 * 
	 * @param expenseString
	 * @return double
	 */
	public double addExpenseTransaction(String expenseString);

	/**
	 * Calculates all the current settlementStatus
	 * 
	 * @return List<Settlement>
	 */
	public List<Settlement> getCurrentSettlementStatus();
	
	/**
	 * Add list of expenses transactions
	 * 
	 * @param expensesFile
	 * @return
	 * @throws IOException 
	 */
	public ExpenseAddResultEntity addExpenseTransactions(File expensesFile) throws IOException;
}
