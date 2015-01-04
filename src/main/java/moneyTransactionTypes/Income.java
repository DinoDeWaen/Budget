package moneyTransactionTypes;

import java.awt.Color;

public class Income implements MoneyTransactionType  {
	

	@Override
	public double getTransactionAmount(double amount) {
		return amount;
	}

	@Override
	public Color getColor() {
		return Color.GREEN;
	}
}
