

import org.joda.time.DateTime;
import org.joda.time.Interval;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import budget.BudgetLine;

public class BudgetLineTest {
    private static final double ACCURACY = 0.00001;
    private static final double amount = 2000;
    private static final DateTime beginDate = new DateTime(2014, 1, 1, 0, 0);
    private static final DateTime endDate = new DateTime(2015, 1, 1, 0, 0);
    private static final DateTime dueDate = new DateTime(2014, 1, 15, 0, 0);  
	
    private static Integer numberOfMonthsBetweenDueDates;  
    private static BudgetLine budgetLine;
    
    private BudgetLine buildBudgetLine() {
	    return BudgetLine.newBuilder()
	    		.withBudgetAmount(amount)
	    		.withBeginDate(beginDate)
	    		.withEndDate(endDate)
	    		.withDueDate(dueDate)        		
	            .withNumberOfMonthsBetweenDueDates(numberOfMonthsBetweenDueDates) 
	            .build();
    }
  
    public class YearlyFrequencyContext {

        @Before
        public void setUp(){
        	numberOfMonthsBetweenDueDates = 12;
        }

        @Test
        public void TestYearlyIncome () {
        	budgetLine = buildBudgetLine();
        	validateYearlyBudgetLine (budgetLine);
        }

        private void validateYearlyBudgetLine(BudgetLine budgetLine) {
            assertEquals(amount, budgetLine.getBudgetAmountInInterval(new Interval(beginDate, endDate)), ACCURACY);	        	
            assertEquals(amount / 12, budgetLine.getMonthlyBudgetAmount(), ACCURACY);
            assertEquals(amount, budgetLine.getYearlyBudgetAmount(), ACCURACY);
        }

    }
    
    public class HalfYearlyFrequencyContext {
    	
        @Before
        public void setUp() {
        	numberOfMonthsBetweenDueDates = 6;
        }

        @Test
        public void addedBudgetLine_canBeRetrieved() {
        	budgetLine = buildBudgetLine();       
            
            validateHalfYearlyBudgetLine(budgetLine);
        }


        private void validateHalfYearlyBudgetLine(BudgetLine budgetLine) {
            assertEquals(amount * 2, budgetLine.getBudgetAmountInInterval(new Interval(beginDate, endDate)), ACCURACY);
            assertEquals(amount / 6, budgetLine.getMonthlyBudgetAmount(), ACCURACY);
            assertEquals(amount * 2, budgetLine.getYearlyBudgetAmount(), ACCURACY);
        }

    }	
    
    public class TrimesterFrequencyContext {
    	
        @Before
        public void setUp() {
        	numberOfMonthsBetweenDueDates = 4;
        }

        @Test
        public void addedBudgetLine_canBeRetrieved() {
        	budgetLine = buildBudgetLine();        
            
            validateHalfYearlyBudgetLine(budgetLine);
        }


        private void validateHalfYearlyBudgetLine(BudgetLine budgetLine) {
            assertEquals(amount * 3, budgetLine.getBudgetAmountInInterval(new Interval(beginDate, endDate)), ACCURACY);
            assertEquals(amount / 4, budgetLine.getMonthlyBudgetAmount(), ACCURACY);
            assertEquals(amount * 3, budgetLine.getYearlyBudgetAmount(), ACCURACY);
        }

    }
    
    public class QuarterlyFrequencyContext {
    	
        @Before
        public void setUp() {
        	numberOfMonthsBetweenDueDates = 3;
        }

        @Test
        public void addedBudgetLine_canBeRetrieved() {
        	budgetLine = buildBudgetLine();         
            
            validateHalfYearlyBudgetLine(budgetLine);
        }


        private void validateHalfYearlyBudgetLine(BudgetLine budgetLine) {
            assertEquals(amount * 4, budgetLine.getBudgetAmountInInterval(new Interval(beginDate, endDate)), ACCURACY);
            assertEquals(amount / 3, budgetLine.getMonthlyBudgetAmount(), ACCURACY);
            assertEquals(amount * 4, budgetLine.getYearlyBudgetAmount(), ACCURACY);
        }

    }	    
    
    public class MonthlyBudgetContext {

        @Before
        public void setUp() throws Exception {
        	numberOfMonthsBetweenDueDates = 1;
        }

        @Test
        public void addedBudgetLine_canBeRetrieved() {
        	budgetLine = buildBudgetLine();         
            
            validateMonthlyBudgetLine(budgetLine);
        }


        private void validateMonthlyBudgetLine(BudgetLine budgetLine) {
            assertEquals(amount * 12, budgetLine.getBudgetAmountInInterval(new Interval(beginDate, endDate)), ACCURACY);
            assertEquals(amount, budgetLine.getMonthlyBudgetAmount(), ACCURACY);
            assertEquals(amount * 12, budgetLine.getYearlyBudgetAmount(), ACCURACY);
        }

    }

}
