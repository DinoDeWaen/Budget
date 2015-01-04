package moneyTransactionTypes;

import java.awt.Color;

public class Expense implements MoneyTransactionType {

	@Override
	public double getTransactionAmount(double amount) {
		return -1 * amount;
	}

	@Override
	public Color getColor() {
		return Color.RED;
	}

}
