package com.movericks.costmanager.services.impl;

import java.util.List;

import com.movericks.costmanager.entities.ExpenseEntity;
import com.movericks.costmanager.entities.Settlement;
import com.movericks.costmanager.entities.UserBalance;
import com.movericks.costmanager.services.ExpenseTransactionService;
import com.movericks.costmanager.services.UserBalanceService;
import com.movericks.costmanager.services.UserService;
import com.movericks.costmanager.utils.SettlementUtils;

public class ExpenseTransactionServiceImpl implements ExpenseTransactionService {

	private static final String PAID_SPLIT_REGEX = "\\s+paid\\s+";
	private static final String FOR_SPLIT_REGEX = "\\s+for\\s+";

	private UserService userService;
	private UserBalanceService userBalanceService;

	public ExpenseTransactionServiceImpl(UserService userService,
			UserBalanceService userBalanceService) {
		this.userService = userService;
		this.userBalanceService = userBalanceService;
	}

	@Override
	public List<Settlement> getCurrentStatus() {
		List<UserBalance> userBalanceList = this.userBalanceService
				.getAllUserBalance();
		return SettlementUtils.settleBalances(userBalanceList);
	}

	@Override
	public double addExpenseTransaction(ExpenseEntity expenseEntity) {

		// Add expense amount as
		this.userBalanceService.addToUser(expenseEntity.getName(),
				expenseEntity.getAmount());

		// headCost for all users
		double expenseCost = expenseEntity.getAmount();
		long totalInvolvedUsers = this.userService.getTotalUser();

		double headCost = expenseCost / totalInvolvedUsers;

		// Subtract headCost from all involved members
		this.userBalanceService.addToAll(-headCost);

		return headCost;
	}

	@Override
	public ExpenseEntity parse(String line) {
		String[] splitedStrs = line.split(PAID_SPLIT_REGEX, 2);
		String name = splitedStrs[0];

		line = splitedStrs[1];
		splitedStrs = line.split(FOR_SPLIT_REGEX, 2);
		String amountWithCurrency = splitedStrs[0];
		String descriptions = splitedStrs[1];
		amountWithCurrency.replaceAll("$", "");

		ExpenseEntity expenseEntity = new ExpenseEntity();
		expenseEntity.setName(name);
		expenseEntity.setCurrency(amountWithCurrency.substring(0, 1));
		expenseEntity.setAmount(Double.parseDouble(amountWithCurrency
				.substring(1)));
		expenseEntity.setDescription(descriptions);

		return expenseEntity;
	}

}
