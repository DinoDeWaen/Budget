package balance;

import java.util.ArrayList;
import java.util.List;

import budget.Budget;


public class BalanceCalculator  implements Balance {
    private List<Budget> budgets = new ArrayList<Budget>();  

	public double calculateTotalBudgetedIncome() {
		double result = 0.0;
		for (Budget b: budgets)
			result += (b.isIncome())?b.getBudgetAmount():0.0;
		
		return result;
	}
	public double calculateTotalBudgetedExpense() {
		double result = 0.0;
		for (Budget b: budgets)
			result += (! b.isIncome())?b.getBudgetAmount():0.0;
		
		return result;
	}
	public double calculateTotalIncome() {
		double result = 0.0;
		for (Budget b: budgets)
			result += (b.isIncome())?b.calculateCashFlowBalance():0.0;
		
		return result;
	}
	public double calculateTotalExpense() {
		double result = 0.0;
		for (Budget b: budgets)
			result += (! b.isIncome())?b.calculateCashFlowBalance():0.0;
		
		return result;
	}
	public void  addBudget(Budget budget) {
		budgets.add(budget);
	} 	

}
