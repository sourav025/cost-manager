package com.mavericks.costmanager.services;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.mavericks.costmanager.entities.ExpenseEntity;
import com.mavericks.costmanager.services.CostManagerService;
import com.mavericks.costmanager.services.impl.CostManagerServiceImpl;

public class CostManagerServiceTest {

	private CostManagerService costManagerService;
	{
		this.costManagerService = new CostManagerServiceImpl();
	}

	public static String[] validUsers = { "Alice", "Bob", "Claire", "David" };
	public static String[] mixUsers = { "Alice", "Bob", "_HELO",
			"invalid==user", "valid user", "     ", "  Claire ", "__David",
			"David" };
	private static boolean[] resultForMixUsers = { true, true, false, false,
			true, false, true, false, true };

	public static String[] expensesEntities = { "Claire paid $100.10 for phone bill.",
			"Bob paid $55.90 for petrol.", "David paid $170.80 for groceries.",
			"David paid $33.40 for breakfast.", "Bob paid $85.60 for lunch.",
			"Claire paid $103.45 for dinner.",
			"Claire paid $30.80 for snacks.",
			"Bob paid $70 for house-cleaning.",
			"David paid $63.50 for utilities." };
	public static ExpenseEntity[] expenseEntities = {};

	public static String[] mixExpenses = { "Claire paid $100.10 for phone bill.",
		"Bob paid $55.90 for petrol.", "David paid -$170.80 for groceries.",
		"David paid $33.40 for breakfast.", "Bob paid $85.60 for lunch.",
		"Claire paid $103.45 for dinner.",
		"Claire paid $30.80 for snacks.",
		"Bob paid $70 for house-cleaning.",
		"David paid $63.50 for utilities."  };
	public static ExpenseEntity[] mixExpenseEntities = {};

	@Test
	public void addUserTest_valid_input() {
		for (String name : validUsers) {
			assertTrue(name + " should be valid.",
					this.costManagerService.addUser(name));
		}
	}

	@Test
	public void addUserTest_mix_input() {
		int total = 0;
		for (int index = 0; index < resultForMixUsers.length; index++) {
			boolean added = false;
			String name=mixUsers[index];
			if(name!=null) name=name.trim();
			try {
				added = this.costManagerService.addUser(name);
				total++;
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			assertTrue(String.format("[%s] is correct.", name),
					resultForMixUsers[index] == added);
		}
		assertEquals(5, total);
	}

	@Test
	public void addExpenseTransactionTest_valid_input() {
		this.addUserTest_valid_input();
		for (String expense : expensesEntities) {
			this.costManagerService.addExpenseTransaction(expense);
		}
	}

	@Test
	public void addExpenseTransactionTest_mix_input() {
		this.addUserTest_mix_input();
		for (String expense : mixExpenses) {
			try{
				this.costManagerService.addExpenseTransaction(expense);}
			catch(Exception exc){
				exc.printStackTrace();
			}
		}
	}

}
