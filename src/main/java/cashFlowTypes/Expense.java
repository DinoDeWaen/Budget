package cashFlowTypes;

import java.awt.Color;

public class Expense implements CashFlowType {

	@Override
	public double addSignToCashFlowAmount(double amount) {
		return -1 * amount;
	}

	@Override
	public Color getColor() {
		return Color.RED;
	}

}
