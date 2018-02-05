package com.movericks.costmanager.entities;

/**
 * All expense Transactions are expressed using ExpenseEntit
 * 
 * @author Sourav
 */
public class ExpenseEntity {
	private String name;
	private String currency;
	private double amount;
	private String description;

	public ExpenseEntity() {
	}

	public ExpenseEntity(String peopleName, double amount) {
		// Using Default Currency is `$`
		// Using Default Description = No Description
		this(peopleName, "$", amount, "No description");
	}

	public ExpenseEntity(String peopleName, String currency, double amount,
			String description) {
		this.name = peopleName;
		this.currency = currency;
		this.amount = amount;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
