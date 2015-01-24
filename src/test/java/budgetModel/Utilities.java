package budgetModel;
import org.joda.time.DateTime;

import budget.Budget;
import cashFlow.BudgetCashFlow;
import cashFlowTypes.CashFlowType;
import cashFlowTypes.Expense;
import cashFlowTypes.Income;


public class Utilities {
	
	
	public static void addCashFlows(Budget budget, double [] incomeAmounts, double [] expenseAmounts) {
		addIncomeCashFlow(budget, incomeAmounts);
		addExpenseCashFlow(budget, expenseAmounts);			 
	}	

	public static void addIncomeCashFlow(Budget budget, double [] list ) {
		addCashFlow(budget, list, new Income() );
	}
	
	public static void addExpenseCashFlow(Budget budget, double [] list ) {
		addCashFlow(budget, list, new Expense() );
	}
	
	private static void addCashFlow(Budget budget, double[] list, CashFlowType type) {
		for (int i = 0; i < list.length; i++){
		     budget.addCashFlow(buildCashFlow(list[i], type));
		 }
	}
	
	private static BudgetCashFlow buildCashFlow(Double cashFlowAmount, CashFlowType type) {
		return cashFlow.MoneyCashFlow.newBuilder()
	                             .withAmount(cashFlowAmount)
	                             .withType(type)
	                             .withDate(new DateTime())
	                             .build();
	}

}
