package budgetModel;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import cashFlowTypes.CashFlowType;
import category.Category;
import balance.Balance;
import balance.BalanceCalculator;
import budget.Budget;
import budget.BudgetCenter;
import budget.BudgetLine;


public class BalanceTest {
	
	Balance balance; 
    private static final DateTime beginDate = new DateTime(2014, 1, 1, 0, 0);
    private static final DateTime endDate = new DateTime(2015, 1, 1, 0, 0);
    private static final DateTime dueDate = new DateTime(2014, 1, 15, 0, 0);
    private static final double incomeAmount  = 3000.00;  
    private static final double expenseAmount = 1500.00;      
    
    private static final int numberOfMonthsBetweenDueDates = 12;  
    
   
	private static final Category incomeCategory = Category.newBuilder().withCategoryName("income").build();
	private static final Category expenseCategory = Category.newBuilder().withCategoryName("expense").build();
	
	private final double [] incomeAmounts = {2000.00, 99.9, 200.34, 43.98};
	private final double [] expenseAmounts = {1000.00, 69.9,20.34, 96.02};	
	
	private static Budget incomeBudget = BudgetCenter.newBuilder().withName("income").withCategory(incomeCategory).withBudgetLine(buildIncomeBudgetLine()).build();
	private static Budget expenseBudget = BudgetCenter.newBuilder().withName("income").withCategory(expenseCategory).withBudgetLine(buildExpenseBudgetLine()).build();
	private static final double ACCURACY = 0.0001;
	
    private static BudgetLine buildIncomeBudgetLine() {
	    return BudgetLine.newBuilder()
	    		.withBudgetAmount(incomeAmount)
	    		.withBeginDate(beginDate)
	    		.withEndDate(endDate)
	    		.withDueDate(dueDate)   
	    		.withCashFlowType(CashFlowType.getCashFlowType(true))
	            .withNumberOfMonthsBetweenDueDates(numberOfMonthsBetweenDueDates) 
	            .build();
    }
    private static BudgetLine buildExpenseBudgetLine() {
	    return BudgetLine.newBuilder()
	    		.withBudgetAmount(expenseAmount)
	    		.withBeginDate(beginDate)
	    		.withEndDate(endDate)
	    		.withDueDate(dueDate)   
	    		.withCashFlowType(CashFlowType.getCashFlowType(false))
	            .withNumberOfMonthsBetweenDueDates(numberOfMonthsBetweenDueDates) 
	            .build();
    }
	
	@Before
	public void setUp ()
	{
	    Utilities.addIncomeCashFlow(incomeBudget, incomeAmounts);
	    Utilities.addExpenseCashFlow(expenseBudget, expenseAmounts);
	}
	
	@Test
	public void createBalanceTest(){
		balance = new BalanceCalculator ();
		balance.addBudget(incomeBudget);
		balance.addBudget(expenseBudget);
		
		assertEquals(balance.calculateTotalBudgetedIncome(), 3000.00, ACCURACY);
		assertEquals(balance.calculateTotalBudgetedExpense(), 1500.00, ACCURACY); 
		assertEquals(balance.calculateTotalIncome(), Arrays.stream(incomeAmounts).sum(), ACCURACY);
		assertEquals(balance.calculateTotalExpense(), Arrays.stream(expenseAmounts).sum() * -1.0, ACCURACY); 		
	}	
}
