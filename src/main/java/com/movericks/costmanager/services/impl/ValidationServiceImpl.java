package com.movericks.costmanager.services.impl;

import java.util.regex.Pattern;

import com.movericks.costmanager.entities.ExpenseEntity;
import com.movericks.costmanager.services.UserService;
import com.movericks.costmanager.services.ValidationService;
import com.movericks.costmanager.utils.CostManagerUtils;

public class ValidationServiceImpl implements ValidationService {

	private UserService userService;

	public ValidationServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean validateName(String name) {
		if (name == null || name.isEmpty() || !Pattern.matches("^[\\p{L}\\s'.-]+$", name)) {
			throw new RuntimeException(
					"Transaction contains invalid user name.");
		}
		return true;
	}

	@Override
	public boolean validateUserFromExpenseEntity(ExpenseEntity expenseEntity) {
		String name = expenseEntity.getName();
		if (name == null || name.isEmpty() || !this.userService.isExists(name)) {
			throw new RuntimeException(
					"Transaction contains invalid user name.");
		}
		return true;
	}

	private boolean amountValidation(ExpenseEntity expenseEntity) {
		if (CostManagerUtils.isLessThan(expenseEntity.getAmount(), 0D)) {
			throw new RuntimeException(String.format(
					"Expense transaction amount %.2f is not Valid.",
					expenseEntity.getAmount()));
		}
		return true;
	}

	@Override
	public boolean validatePayment(ExpenseEntity expenseEntity) {
		return amountValidation(expenseEntity)
				&& this.validateUserFromExpenseEntity(expenseEntity);
	}

}
