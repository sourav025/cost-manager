package com.movericks.costmanager.services.impl;

import java.util.HashSet;
import java.util.Set;

import com.movericks.costmanager.services.UserService;

public class UserServiceImpl implements UserService {

	// Assume data services on Memory
	private Set<String> nameMap;

	// Initialization of nameMap
	{
		this.nameMap = new HashSet<String>();
	}

	private boolean isNotEmpty(String name) {
		return name != null && !name.isEmpty();
	}

	@Override
	public boolean isExists(String name) {
		return this.isNotEmpty(name) && this.nameMap.contains(name);
	}

	@Override
	public boolean registerUser(String name) {
		return this.nameMap.add(name);
	}

	@Override
	public long getTotalUser() {
		return this.nameMap.size();
	}

}
