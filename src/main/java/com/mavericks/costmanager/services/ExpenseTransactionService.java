package com.mavericks.costmanager.services;

import java.util.List;

import com.mavericks.costmanager.entities.ExpenseEntity;
import com.mavericks.costmanager.entities.Settlement;

public interface ExpenseTransactionService {

	/**
	 * 
	 * Add expenseTransaction to CostManager On successfully addition, this
	 * method returns the headCost added for All Users
	 * 
	 * @param expenseEntity
	 * @return double
	 */
	public double addExpenseTransaction(ExpenseEntity expenseEntity);

	/**
	 * Calculates all User
	 * 
	 * @return List<Settlement>
	 */
	public List<Settlement> getCurrentStatus();

	/**
	 * Parse line of Type String to ExpenseEntity
	 * 
	 * @param line
	 * @return ExpenseEntity
	 */
	public ExpenseEntity parse(String line);
}
