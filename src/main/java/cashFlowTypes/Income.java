package cashFlowTypes;

import java.awt.Color;

public class Income implements CashFlowType, java.io.Serializable {
	
	@Override
	public double addSignToCashFlowAmount(double amount) {
		return amount;
	}

	@Override
	public Color getColor() {
		return Color.GREEN;
	}

	@Override
	public boolean isIncome() {
		return true;
	}
}
