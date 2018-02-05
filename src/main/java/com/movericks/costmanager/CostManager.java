package com.movericks.costmanager;

import java.io.File;
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
		File namesFile = new File(
				"/Users/sourav/Desktop/input/custom/names.txt");
		// Input Expense Transactions List from File
		File expensesListsFile = new File(
				"/Users/sourav/Desktop/input/custom/payments.txt");

		// Add all users First
		costManager.addUsers(namesFile);

		// Add all expenses from file
		costManager.addExpenses(expensesListsFile);

		// showing all settlement status
		costManager.getSettlementStatus().stream().forEach(System.out::println);
	}

	/**
	 * Add Users from file
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public UserAddResultEntity addUsers(File file) throws IOException {
		UserAddResultEntity userAddResultEntity = costManagerService
				.addUsers(file);
		if (userAddResultEntity.getFailed() != 0) {
			System.err.printf("[WARNING] Failed to Register %d Users\n",
					userAddResultEntity.getFailed());
			userAddResultEntity.getFailedLists().stream().map(failed->"[Skipped] "+failed)
					.forEach(System.err::println);
		}
		return userAddResultEntity;
	}

	/**
	 * Add All expenses from file
	 * @param file
	 * @return
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
	 * @return
	 */
	public List<Settlement> getSettlementStatus() {
		return costManagerService.getCurrentSettlementStatus();
	}
}
