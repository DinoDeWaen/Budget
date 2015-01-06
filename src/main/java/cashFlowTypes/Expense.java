package cashFlowTypes;

import java.awt.Color;

public class Expense implements CashFlowType {

	@Override
	public double getTransactionAmount(double amount) {
		return -1 * amount;
	}

	@Override
	public Color getColor() {
		return Color.RED;
	}

}
