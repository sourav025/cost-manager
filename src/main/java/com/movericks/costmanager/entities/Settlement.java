package com.movericks.costmanager.entities;

public class Settlement {

	private static final String TO_STRING = "%s pays $%.2f to %s.";

	private final String payerName;
	private final String payeeName;
	private final double settledAmount;

	public Settlement(String payerName, String payeeName, double amount) {
		this.payerName = payerName;
		this.payeeName = payeeName;
		this.settledAmount = amount;
	}

	public String getPayerName() {
		return payerName;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public double getAmount() {
		return settledAmount;
	}

	@Override
	public String toString() {
		return String.format(TO_STRING, this.payerName, this.settledAmount,
				this.payeeName);
	}
}
