package com.mavericks.costmanager.services.impl;

import java.util.HashSet;
import java.util.Set;

import com.mavericks.costmanager.services.UserService;

public class UserServiceImpl implements UserService {
	
	// Assume data services on Memory
	private Set<String> nameMap;

	// Initialization of nameMap
	{
		this.nameMap = new HashSet<String>();
	}

	@Override
	public boolean isExists(String name) {
		return this.nameMap.contains(name);
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
