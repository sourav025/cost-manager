package com.movericks.costmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.movericks.costmanager.entities.ExpenseAddResultEntity;
import com.movericks.costmanager.entities.Settlement;
import com.movericks.costmanager.entities.UserAddResultEntity;
import com.movericks.costmanager.services.CostManagerService;
import com.movericks.costmanager.services.impl.CostManagerServiceImpl;

/**
 * CostManager Application starts initialization from here
 * 
 * @author Sourav
 */

public class CostManager {
	
	private static final String NAMES_FILE="names";
	private static final String EXPENSES_FILE="expenses";
	
	/** 
	 * Service Intialization is completed here.
	 */
	private final CostManagerService costManagerService;
	{
		costManagerService = new CostManagerServiceImpl();
	}

	public static void main(String[] args) throws IOException {

		CostManager costManager = new CostManager();
		
		// Input Names List From File
		File namesFile = costManager.getFileFromSystem(NAMES_FILE);

		// Input Expense Transactions List from File
		File expensesListsFile = costManager.getFileFromSystem(EXPENSES_FILE);

		// Add all users First
		costManager.addUsers(namesFile);

		// Add all expenses from file
		costManager.addExpenses(expensesListsFile);

		// showing all settlement status
		costManager.getSettlementStatus().stream().forEach(System.out::println);
	}

	private File getFileFromSystem(String parameterName) throws FileNotFoundException {
		String path=System.getProperty(parameterName);
		
		File file = new File(path);
		if(!file.exists()) 
			throw new FileNotFoundException(String.format("File not found %s", path));
		return file;
	}

	/**
	 * Add Users from file
	 * 
	 * @param file
	 * @return UserAddResultEntity
	 * @throws IOException
	 */
	public UserAddResultEntity addUsers(File file) throws IOException {
		UserAddResultEntity userAddResultEntity = costManagerService
				.addUsers(file);
		if (userAddResultEntity.getFailed() != 0) {
			System.err.printf("[WARNING] Failed to Register %d Users\n",
					userAddResultEntity.getFailed());
			userAddResultEntity.getFailedLists().stream()
					.map(failed -> "[Skipped] " + failed)
					.forEach(System.err::println);
		}
		return userAddResultEntity;
	}

	/**
	 * Add All expenses from file
	 * 
	 * @param file
	 * @return ExpenseAddResultEntity
	 * @throws IOException
	 */
	public ExpenseAddResultEntity addExpenses(File file) throws IOException {
		ExpenseAddResultEntity resultEntity = costManagerService
				.addExpenseTransactions(file);
		if (resultEntity.getFailed() != 0) {
			System.err.printf("[WARNING] Failed to add %d expenses\n",
					resultEntity.getFailed());
			resultEntity.getFailedExpensesList().stream()
					.map(failedExpense -> "[Skipped] " + failedExpense)
					.forEach(System.err::println);
		}
		return resultEntity;
	}

	/**
	 * Return All settlement Status
	 * 
	 * @return List<Settlement>
	 */
	public List<Settlement> getSettlementStatus() {
		return costManagerService.getCurrentSettlementStatus();
	}
}
