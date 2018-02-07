package com.mavericks.costmanager.services.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mavericks.costmanager.entities.ExpenseAddResultEntity;
import com.mavericks.costmanager.entities.ExpenseEntity;
import com.mavericks.costmanager.entities.Settlement;
import com.mavericks.costmanager.entities.UserAddResultEntity;
import com.mavericks.costmanager.services.CostManagerService;
import com.mavericks.costmanager.services.ExpenseTransactionService;
import com.mavericks.costmanager.services.UserBalanceService;
import com.mavericks.costmanager.services.UserService;
import com.mavericks.costmanager.services.ValidationService;
import com.mavericks.costmanager.utils.CostManagerUtils;

public class CostManagerServiceImpl implements CostManagerService {

	private UserService userService;
	private UserBalanceService userBalanceService;
	private ValidationService validationService;
	private ExpenseTransactionService expenseTransactionService;

	public CostManagerServiceImpl() {
		initializeBeans();
	}

	@Override
	public boolean addUser(String name) {
		this.validationService.validateName(name);
		boolean registrationResult = this.userService.registerUser(name);
		if (registrationResult) {
			// Set Initial Balance 0.0
			this.userBalanceService.setUserBalance(name, 0D);
		}
		else {
			throw new RuntimeException(String.format("Name :\"%s\" Already Exists.", name));
		}
		return registrationResult;
	}

	@Override
	public double addExpenseTransaction(ExpenseEntity expenseEntity) {
		this.validationService.validatePayment(expenseEntity);
		return this.expenseTransactionService
				.addExpenseTransaction(expenseEntity);
	}

	@Override
	public List<Settlement> getCurrentSettlementStatus() {
		return this.expenseTransactionService.getCurrentStatus();
	}

	private void initializeBeans() {
		this.userService = new UserServiceImpl();
		this.userBalanceService = new UserBalanceServiceImpl();
		this.validationService = new ValidationServiceImpl(this.userService);
		this.expenseTransactionService = new ExpenseTransactionServiceImpl(
				this.userService, this.userBalanceService);
	}

	@Override
	public double addExpenseTransaction(String expenseString) {
		ExpenseEntity expenseEntity = expenseTransactionService
				.parse(expenseString);
		return this.addExpenseTransaction(expenseEntity);
	}

	@Override
	public UserAddResultEntity addUsers(File usersFile) throws IOException {
		BufferedReader reader = CostManagerUtils.getReader(usersFile);
		String line = null;
		long successful = 0;
		long failed = 0;
		List<String> failedUsersList = new ArrayList<String>();
		while ((line = reader.readLine()) != null) {
			String name = line.trim();
			try {
				this.addUser(name);
				successful++;
			} catch (RuntimeException exception) {
				failed++;
				failedUsersList.add(line);
			}
		}
		CostManagerUtils.close(reader);
		return new UserAddResultEntity(successful, failed, failedUsersList);
	}

	@Override
	public ExpenseAddResultEntity addExpenseTransactions(File expensesFile)
			throws IOException {
		BufferedReader reader = CostManagerUtils.getReader(expensesFile);
		String line = null;

		long successful = 0;
		long failed = 0;
		List<String> failedExpenseTransactionsList = new ArrayList<String>();

		while ((line = reader.readLine()) != null) {
			String expenseString = line.trim();
			try {
				this.addExpenseTransaction(expenseString);
				successful++;
			} catch (Exception exc) {
				failed++;
				failedExpenseTransactionsList.add(line);
			}
		}
		CostManagerUtils.close(reader);
		return new ExpenseAddResultEntity( successful, failed,
				failedExpenseTransactionsList);

	}
}
