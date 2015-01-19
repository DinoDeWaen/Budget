import static org.junit.Assert.*;
import gateway.BudgetDataBase;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import cashFlowTypes.CashFlowType;
import category.Category;
import balance.Balance;
import budget.Budget;
import budget.BudgetLine;


public class BalanceTest {
	
	Balance balance; 
    private static final DateTime beginDate = new DateTime(2014, 1, 1, 0, 0);
    private static final DateTime endDate = new DateTime(2015, 1, 1, 0, 0);
    private static final DateTime dueDate = new DateTime(2014, 1, 15, 0, 0);
    private static final double incomeAmount  = 2000.00;  
    private static final double expenseAmount = 1000.00;      
    
    private static final int numberOfMonthsBetweenDueDates = 12;  
    
   
	private static final Category incomeCategory = Category.newBuilder().withCategoryName("income").build();
	private static final Category expenseCategory = Category.newBuilder().withCategoryName("expense").build();
	
	private final double [] incomeAmounts = {2000.00, 99.9, 200.34, 43.98};
	private final double [] expenseAmounts = {1000.00, 69.9,20.34, 96.02};	
	
	private static Budget incomeBudget = Budget.newBuilder().withName("income").withCategory(incomeCategory).build();
	private static Budget expenseBudget = Budget.newBuilder().withName("income").withCategory(expenseCategory).build();
	private static final double ACCURACY = 0.00001;
	
    private BudgetLine buildIncomeBudgetLine() {
	    return BudgetLine.newBuilder()
	    		.withBudgetAmount(incomeAmount)
	    		.withBeginDate(beginDate)
	    		.withEndDate(endDate)
	    		.withDueDate(dueDate)   
	    		.withCashFlowType(CashFlowType.getCashFlowType(true))
	            .withNumberOfMonthsBetweenDueDates(numberOfMonthsBetweenDueDates) 
	            .build();
    }
    private BudgetLine buildExpenseBudgetLine() {
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


	}
	
	@Test
	public void createBalanceTest(){
		balance = new Balance ();
		assertEquals(balance.getTotalBudgetIncome(), 2000.00, ACCURACY);
		assertEquals(balance.getTotalBudgetExpense(), 1000.00, ACCURACY); 
		assertEquals(balance.getTotalIncome(), 1582.55, ACCURACY);
		assertEquals(balance.getTotalExpense(), 685.57, ACCURACY); 		
	}	
}
