package com.mavericks.costmanager.utils;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.movericks.costmanager.entities.UserBalance;
import com.movericks.costmanager.utils.SettlementUtils;

@RunWith(Theories.class)
public class SettlementUtilsTest {

	private static int TEST_NO = 0;

	@Before
	public void setup() {
		TEST_NO++;
	}

	private static double cases[][] = { { -100, -300, 600, -200 },
			{ -500, 100, -100, 500 }, { -1000, 500, 300, 200 },
			{ -200, 0, 100, 100 }, { -400, 400, 0, 0 } };
	private static String[] caseResult = {
			"[B pays $300.00 to C., D pays $200.00 to C., A pays $100.00 to C.]",
			"[A pays $500.00 to D., C pays $100.00 to B.]",
			"[A pays $500.00 to B., A pays $300.00 to C., A pays $200.00 to D.]",
			"[A pays $100.00 to D., A pays $100.00 to C.]",
			"[A pays $400.00 to B.]" };

	@DataPoints
	public static List<UserBalance>[] dataResult() {
		List<List<UserBalance>> caseResult = new ArrayList<List<UserBalance>>();
		List<UserBalance>[] lists = new List[cases.length];

		for (int cas = 0; cas < cases.length; cas++) {
			List<UserBalance> userBalanceList = new ArrayList<UserBalance>();
			for (int i = 0; i < cases[cas].length; i++) {
				UserBalance userBalance = new UserBalance(
						Character.toString((char) ('A' + i)), cases[cas][i]);
				userBalanceList.add(userBalance);
			}
			caseResult.add(userBalanceList);
		}
		return caseResult.toArray(lists);
	}

	@Theory
	public void settleBalancesTest(List<UserBalance> userBalanceList) {
		String result = SettlementUtils.settleBalances(userBalanceList)
				.toString();
		String expected = caseResult[TEST_NO - 1];
		assertEquals(expected, result);
	}
}
