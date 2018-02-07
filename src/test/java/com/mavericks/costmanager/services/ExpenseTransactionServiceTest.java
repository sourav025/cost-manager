package com.mavericks.costmanager.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.mavericks.costmanager.entities.ExpenseEntity;
import com.mavericks.costmanager.services.ExpenseTransactionService;
import com.mavericks.costmanager.services.UserBalanceService;
import com.mavericks.costmanager.services.UserService;
import com.mavericks.costmanager.services.impl.ExpenseTransactionServiceImpl;
import com.mavericks.costmanager.services.impl.UserBalanceServiceImpl;
import com.mavericks.costmanager.services.impl.UserServiceImpl;
import com.mavericks.costmanager.utils.CostManagerUtils;

@RunWith(Theories.class)
public class ExpenseTransactionServiceTest {
	
	private UserService userService;
	private UserBalanceService userBalanceService;
	private ExpenseTransactionService expenseTransactionService;

	private static final String ENTITY_CHECK_FORMAT = "%s %s%.2f";

	@Test
	public void addExpenseTransactionEntityTest() {
		String[] users={"Alice","Bob","Claire", "David"};
		Arrays.stream(users).forEach(name->{
			userService.registerUser(name);
			userBalanceService.setUserBalance(name, 0.0D);
		});
		for(int i=0;i<validExpenseString.length;i++){
			ExpenseEntity entity = expenseTransactionService.parse(validExpenseString[i]);
			double result = expenseTransactionService.addExpenseTransaction(entity);
			assertTrue(CostManagerUtils.isZero(result - validResults[i]));
		}
	}

	@Theory
	public void parseTestInputValid(
			@FromDataPoints("validExpenseString") String expenseString) {
		ExpenseEntity expenseEntity = this.expenseTransactionService
				.parse(expenseString);
		int index = findEnteredStringIndex(validExpenseString, expenseString);
		String expr = String.format(ENTITY_CHECK_FORMAT,
				expenseEntity.getName(), expenseEntity.getCurrency(),
				expenseEntity.getAmount());
		assertEquals(validExpressionResult[index], expr);
	}

	@Theory
	public void parseTestInputMix(
			@FromDataPoints("mixExpenseString") String expenseString) {
		ExpenseEntity expenseEntity = this.expenseTransactionService
				.parse(expenseString);
		String expr = String.format(ENTITY_CHECK_FORMAT,
				expenseEntity.getName(), expenseEntity.getCurrency(),
				expenseEntity.getAmount());
		int index = findEnteredStringIndex(mixExpenseString, expenseString);
		assertEquals(mixExpenseStringResult[index], expr);
	}

	private static int findEnteredStringIndex(String[] expenseStringList,
			String targetExpenseString) {
		int index;
		for (index = 0; index < expenseStringList.length; index++) {
			if (expenseStringList[index].equals(targetExpenseString)) {
				return index;
			}
		}
		return -1;
	}

	@Before
	public void setup() {
		this.userService = new UserServiceImpl();
		this.userBalanceService = new UserBalanceServiceImpl();
		this.expenseTransactionService = new ExpenseTransactionServiceImpl(
				this.userService, this.userBalanceService);
	}

	@DataPoints("validExpenseString")
	public static String[] validExpenseString = {
			"Claire paid $100.10 for phone bill.",
			"Bob paid $55.90 for petrol.", "David paid $170.80 for groceries.",
			"David paid $33.40 for breakfast.", "Bob paid $85.60 for lunch.",
			"Claire paid $103.45 for dinner.",
			"Claire paid $30.80 for snacks.",
			"Bob paid $70 for house-cleaning.",
			"David paid $63.50 for utilities." };
	private static String[] validExpressionResult = { "Claire $100.10",
			"Bob $55.90", "David $170.80", "David $33.40", "Bob $85.60",
			"Claire $103.45", "Claire $30.80", "Bob $70.00", "David $63.50" };
	private static double[] validResults={25.025D,13.975D,42.7D,8.35D,21.4D,25.8625D,7.7D,17.5D,15.875D};

	@DataPoints("mixExpenseString")
	public static String[] mixExpenseString = {
			"Claire paid $100.1 for phone bill.",
			"Christina Perry paid $0 for petrol.",
			"David paid $170.80 for groceries.",
			"David paid $-33.40 for breakfast.", "Bob paid $85.60 for lunch.",
			"Michael Jackson paid $103.45 for dinner.",
			"Claire paid $-30.80 for snacks.",
			"Bob paid $70 for house-cleaning.",
			"Mobid paid $63.50 for utilities." };
	private static String[] mixExpenseStringResult = { "Claire $100.10",
			"Christina Perry $0.00", "David $170.80", "David $-33.40",
			"Bob $85.60", "Michael Jackson $103.45", "Claire $-30.80",
			"Bob $70.00", "Mobid $63.50" };

}
