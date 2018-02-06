package com.mavericks.costmanager.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.movericks.costmanager.services.UserService;
import com.movericks.costmanager.services.impl.UserServiceImpl;

@RunWith(Theories.class)
public class UserServiceTest {
	private UserService userService;

	@DataPoints("validNames")
	public static String[] validNames = { "David", "Michael Jackson", "json",
			"Lady Gaga", "Taylor", "Taylor Swift", };

	@Before
	public void setup() {
		this.userService = new UserServiceImpl();
	}

	@Test
	public void getTotalUser() {
		Arrays.stream(validNames).forEach(name -> {
			this.userService.registerUser(name);
		});
		assertEquals(validNames.length,
				this.userService.getTotalUser(), validNames.length);
	}
	
	@Theory
	public void isExistsTest(String name) {
		assertFalse(String.format("%s should not present here", name),
				this.userService.isExists(name));
	}

	@Test
	public void isExistsTest2() {
		Arrays.stream(validNames).forEach(name -> {
			this.userService.registerUser(name);
		});
		Arrays.stream(validNames).forEach(name -> {
			assertTrue(this.userService.isExists(name));
		});
	}
	
	@Theory
	public void registerUserTest(String name) {
		assertTrue(name + " Should be registered correctly",
				this.userService.registerUser(name));
	}
}
