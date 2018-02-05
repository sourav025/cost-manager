package com.movericks.costmanager.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose of this entity is to express result 
 * while adding multiple expense transactions
 * @author sourav
 *
 */
public class ExpenseAddResultEntity {
	private long successful;
	private long failed;
	private List<String> failedExpensesList;
	
	public ExpenseAddResultEntity(){
		failedExpensesList=new ArrayList<String>();
	};
	
	public ExpenseAddResultEntity(long successful, long failed,
			List<String> failedExpensesList) {
		super();
		this.successful = successful;
		this.failed = failed;
		this.failedExpensesList = failedExpensesList;
	}
	
	public long getSuccessful() {
		return successful;
	}
	public void setSuccessful(long successful) {
		this.successful = successful;
	}
	public long getFailed() {
		return failed;
	}
	public void setFailed(long failed) {
		this.failed = failed;
	}
	public List<String> getFailedExpensesList() {
		return failedExpensesList;
	}
	public void setFailedExpensesList(List<String> failedExpensesList) {
		this.failedExpensesList = failedExpensesList;
	}
}
