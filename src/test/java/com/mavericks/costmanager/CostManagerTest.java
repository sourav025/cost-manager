package com.mavericks.costmanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mavericks.costmanager.CostManager;
import com.mavericks.costmanager.entities.ExpenseAddResultEntity;
import com.mavericks.costmanager.entities.Settlement;
import com.mavericks.costmanager.entities.UserAddResultEntity;

/**
 * Unit test for simple App.
 */
public class CostManagerTest {

	private CostManager costManager;

	@Before
	public void setup() {
		costManager = new CostManager();
	}

	private File getFile(String filePath) {
		return new File(ClassLoader.getSystemResource(filePath).getFile());
	}

	private UserAddResultEntity addUsers(String filePath) throws IOException {
		return costManager.addUsers(getFile(filePath));
	}

	private ExpenseAddResultEntity addExpenses(String filePath)
			throws IOException {
		File expensesFile = this.getFile(filePath);
		ExpenseAddResultEntity resultEntity = costManager
				.addExpenses(expensesFile);
		return resultEntity;
	}

	/**
	 * @param fileName
	 *            { File should be present in /src/test/resources to do JUNIT}
	 */
	@Test
	public void testAddUsers() throws IOException {
		// User Files
		String userFilePath = "./case1/names.txt";
		UserAddResultEntity entity = this.addUsers(userFilePath);
		assertEquals(0L, entity.getFailed());
		assertEquals(4L, entity.getSuccessful());
		assertTrue(entity.getFailedLists().size() == 0);
	}

	/**
	 * @param fileName
	 *            { File should be present in /src/test/resources to do JUNIT}
	 * @throws IOException
	 */
	@Test
	public void testAddExpenses() throws IOException {
		String userFilePath = "./case1/names.txt";
		String expensesPath = "./case1/payments.txt";
		this.addUsers(userFilePath);
		ExpenseAddResultEntity expenseAddResultEntity = this
				.addExpenses(expensesPath);
		assertEquals(0, expenseAddResultEntity.getFailed());
		assertEquals(9, expenseAddResultEntity.getSuccessful());
	}

	/**
	 * Test Settlement Status
	 */
	@Test
	public void testgetSettlementStatus() throws IOException {
		
		String userFilePath = "./case1/names.txt";
		String expensesPath = "./case1/payments.txt";
		
		addUsers(userFilePath);
		addExpenses(expensesPath);
		List<Settlement> settlements = costManager.getSettlementStatus();
		assertEquals(3, settlements.size());
		String expectedResult ="[Alice pays $89.31 to David., Alice pays $55.96 to Claire., Alice pays $33.11 to Bob.]";
		assertEquals(expectedResult, settlements.toString());
	}

	/**
	 * Test Settlement Status case2
	 * Duplicate name and skip that
	 */
	@Test
	public void testgetSettlementStatus_case2() throws IOException {

		String userFilePath = "./case2/names.txt";
		String expensesPath = "./case2/payments.txt";

		// User Add Validation
		UserAddResultEntity userAddResultEntity = addUsers(userFilePath);
		assertEquals(2L, userAddResultEntity.getFailed());
		assertEquals(4L, userAddResultEntity.getSuccessful());
		assertTrue(userAddResultEntity.getFailedLists().size() == 2);
		assertEquals("[Alice, Bob]", userAddResultEntity.getFailedLists().toString());
		
		// Expense Add validation
		ExpenseAddResultEntity expenseResult = addExpenses(expensesPath);
		assertEquals(0, expenseResult.getFailed());
		assertEquals(9, expenseResult.getSuccessful());

		// Settlement Validation
		List<Settlement> settlements = costManager.getSettlementStatus();
		assertEquals(3, settlements.size());
		assertEquals(
				"[David pays $55.11 to Bob., Claire pays $10.04 to Alice., David pays $4.38 to Alice.]",
				settlements.toString());
	}

	/**
	 * Test Settlement Status case3
	 * Checks for negative transactions
	 */
	@Test
	public void testgetSettlementStatus_case3() throws IOException {
		String userFilePath = "./case3/names.txt";
		String expensesPath = "./case3/payments.txt";

		// User Add Validation
		UserAddResultEntity userAddResultEntity = addUsers(userFilePath);
		assertEquals(0L, userAddResultEntity.getFailed());
		assertEquals(4L, userAddResultEntity.getSuccessful());
		assertEquals(0, userAddResultEntity.getFailedLists().size());
		
		// Expense Add validation
		ExpenseAddResultEntity expenseResult = addExpenses(expensesPath);
		assertEquals(2, expenseResult.getFailed());
		assertEquals(8, expenseResult.getSuccessful());

		// Settlement Validation
		List<Settlement> settlements = costManager.getSettlementStatus();
		assertEquals(3, settlements.size());
		assertEquals(
				"[Alice pays $114.34 to David., Alice pays $39.03 to Bob., Claire pays $19.11 to Bob.]",
				settlements.toString());
	}
}
