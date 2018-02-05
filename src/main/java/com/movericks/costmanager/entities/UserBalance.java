package com.movericks.costmanager.entities;

/**
 * Entity to keep track user current balance
 * 
 * @author Sourav
 *
 */
public class UserBalance implements Comparable<UserBalance> {
	private static final double EPS = 1e-7;

	private String userName;
	private double amount;

	public UserBalance() {
	}

	public UserBalance(String userName, double amount) {
		this.userName = userName;
		this.amount = amount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public int compareTo(UserBalance userBalance) {
		if (Math.abs(userBalance.amount - amount) <= EPS) {
			return 0;
		}
		return Double.valueOf(amount).compareTo(userBalance.amount);
	}
}
