package balance;

import java.util.ArrayList;
import java.util.List;

import cashFlow.CashFlow;
import budget.Budget;


public class Balance {
    private List<Budget> budgets = new ArrayList<Budget>();  

	public double getTotalBudgetIncome() {
		double result = 0.0;
		for (Budget b: budgets)
			result += (b.isIncome())?b.getBudgetAmount():0.0;
		
		return result;
	}
	public double getTotalBudgetExpense() {
		double result = 0.0;
		for (Budget b: budgets)
			result += (! b.isIncome())?b.getBudgetAmount():0.0;
		
		return result;
	}
	public double getTotalIncome() {
		double result = 0.0;
		for (Budget b: budgets)
			result += (b.isIncome())?b.getBalance():0.0;
		
		return result;
	}
	public double getTotalExpense() {
		double result = 0.0;
		for (Budget b: budgets)
			result += (! b.isIncome())?b.getBalance():0.0;
		
		return result;
	}
	public void  addBudget(Budget budget) {
		budgets.add(budget);
	} 	

}
