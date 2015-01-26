package balance;

import budget.Budget;

public interface Balance{
	double calculateTotalBudgetedIncome(); 
	double calculateTotalBudgetedExpense(); 
	double calculateTotalIncome(); 
	double calculateTotalExpense(); 
    void  addBudget(Budget budget); 
}
