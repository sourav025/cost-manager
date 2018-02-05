package com.movericks.costmanager.services;

import java.util.List;

import com.movericks.costmanager.entities.ExpenseEntity;
import com.movericks.costmanager.entities.Settlement;

public interface ExpenseTransactionService {

	/**
	 * 
	 * Add expenseTransaction to CostManager On successfully addition, this
	 * method returns the headCost added for All Users
	 * 
	 * @param expenseEntity
	 * @return
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
	 * @return
	 */
	public ExpenseEntity parse(String line);
}
