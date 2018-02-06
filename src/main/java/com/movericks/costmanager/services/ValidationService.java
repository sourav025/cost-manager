package com.movericks.costmanager.services;

import com.movericks.costmanager.entities.ExpenseEntity;

/**
 * 
 * Validations of Users and Expense Transactions
 * 
 * @author sourav
 *
 */
public interface ValidationService {

	/**
	 * Validation of Expense Transaction
	 * 
	 * @param payment
	 * @return boolean
	 */
	public boolean validatePayment(ExpenseEntity payment);

	/**
	 * Validation of name
	 * 
	 * @param name
	 * @return boolean
	 */
	public boolean validateName(String name);

	/**
	 * Expense Transaction validation using ExpenseEntity
	 * 
	 * @param expenseEntity
	 * @return boolean
	 */
	public boolean validateUserFromExpenseEntity(ExpenseEntity expenseEntity);
}
