package com.mavericks.costmanager.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mavericks.costmanager.entities.Settlement;
import com.mavericks.costmanager.entities.UserBalance;

/**
 * Purpose of this Utility class is to settle the balances between involved
 * people. This function will clarify who should pay to whom.
 * 
 * @author Sourav
 *
 */
public class SettlementUtils {

	public static List<Settlement> settleBalances(
			List<UserBalance> userBalanceList) {

		Collections.sort(userBalanceList);

		UserBalance[] balances = new UserBalance[userBalanceList.size()];
		balances = userBalanceList.toArray(balances);

		List<Settlement> settlements = new ArrayList<Settlement>();

		int leftIterator = 0;
		int rightIterator = balances.length - 1;

		while (leftIterator < rightIterator) {

			double leftValue = balances[leftIterator].getAmount();
			double rightValue = balances[rightIterator].getAmount();

			if (leftValue > 0)
				break;
			if (rightValue < 0)
				break;

			if (CostManagerUtils.isZero(leftValue)) {
				leftIterator++;
				continue;
			}
			if (CostManagerUtils.isZero(rightValue)) {
				rightIterator--;
				continue;
			}

			double value = leftValue + rightValue;

			// both value same
			if (CostManagerUtils.isZero(value)) {
				Settlement settlement = new Settlement(
						balances[leftIterator].getUserName(),
						balances[rightIterator].getUserName(), rightValue);
				settlements.add(settlement);
				leftIterator++;
				rightIterator--;
			}

			// totalSum < 0 so left as it is ( try swap with right), right move
			// left
			else if (value < 0) {
				Settlement settlement = new Settlement(
						balances[leftIterator].getUserName(),
						balances[rightIterator].getUserName(), rightValue);
				settlements.add(settlement);
				rightIterator--;
				balances[leftIterator].setAmount(value);

				// Try swapping with right
				int tmp = leftIterator;
				while (tmp + 1 < rightIterator
						&& CostManagerUtils.isLessThan(
								balances[tmp + 1].getAmount(), 0D)
						&& CostManagerUtils.isLessThan(
								balances[tmp + 1].getAmount(),
								balances[tmp].getAmount())) {
					UserBalance tmpBalance = balances[tmp + 1];
					balances[tmp + 1] = balances[tmp];
					balances[tmp] = tmpBalance;
					tmp++;
				}
			}

			// totalSum > 0 so left move right, right as it is (try swap with
			// left)
			else {
				Settlement settlement = new Settlement(
						balances[leftIterator].getUserName(),
						balances[rightIterator].getUserName(),
						Math.abs(leftValue));
				settlements.add(settlement);
				leftIterator++;
				balances[rightIterator].setAmount(value);

				// Try swapping with left
				int tmp = rightIterator;
				while (leftIterator < tmp - 1
						&& CostManagerUtils.isLessThan(0D,
								balances[tmp - 1].getAmount())
						&& CostManagerUtils.isLessThan(
								balances[tmp].getAmount(),
								balances[tmp - 1].getAmount())) {
					UserBalance tmpBalance = balances[tmp];
					balances[tmp] = balances[tmp - 1];
					balances[tmp - 1] = tmpBalance;
					tmp--;
				}
			}
		}
		return settlements;
	}

}
