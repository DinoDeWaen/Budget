package budget;

import cashFlow.BudgetCashFlow;

public interface Budget {
	boolean isIncome ();
	double getBudgetAmount();
	double calculateCashFlowBalance();
	void  addCashFlow(BudgetCashFlow cashFlow) ;

}
