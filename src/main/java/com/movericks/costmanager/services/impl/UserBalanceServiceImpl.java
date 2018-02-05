package com.movericks.costmanager.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.movericks.costmanager.entities.UserBalance;
import com.movericks.costmanager.services.UserBalanceService;

public class UserBalanceServiceImpl implements UserBalanceService {

	/**
	 * userBalanceMap is used to stored all users current balance
	 * 
	 * [NOTE] Assume stores in memory
	 */
	private Map<String, Double> userBalanceMap;
	{
		this.userBalanceMap = new HashMap<String, Double>();
	}

	@Override
	public void setUserBalance(String name, double newBalance) {
		this.userBalanceMap.put(name, newBalance);
	}

	@Override
	public void addToUser(String name, double amount) {
		double newAmount = this.userBalanceMap.get(name) + amount;
		this.userBalanceMap.put(name, newAmount);
	}

	@Override
	public void addToAll(double amount) {
		for (String name : this.userBalanceMap.keySet()) {
			this.addToUser(name, amount);
		}
	}

	@Override
	public List<UserBalance> getAllUserBalance() {
		return this.userBalanceMap
				.entrySet()
				.stream()
				.map(userBalanceEntry -> new UserBalance(userBalanceEntry
						.getKey(), userBalanceEntry.getValue()))
				.collect(Collectors.toList());
	}
}
