package cashFlowTypes;

import java.awt.Color;

public interface CashFlowType  {
	double addSignToCashFlowAmount (double amount);
	boolean isIncome ();	
	Color getColor();
	
	
    static CashFlowType getCashFlowType(boolean income) {
		if (income)
			return new Income();
		else
			return new Expense();
	}
	
}
