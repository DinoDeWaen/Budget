import de.bechte.junit.runners.context.HierarchicalContextRunner;
import budget.BudgetLine;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;



@RunWith(HierarchicalContextRunner.class)
public class BudgetLineTest {
    private static final double ACCURACY = 0.00001;
    private static final double amount = 2000;
    private static final DateTime beginDate = new DateTime(2014, 1, 1, 0, 0);
    private static final DateTime endDate = new DateTime(2015, 1, 1, 0, 0);
    private static final DateTime dueDate = new DateTime(2014, 1, 15, 0, 0);  
	
    private static int numberOfMonthsBetweenDueDates;  
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
    
	private void validateDates(BudgetLine budgetLine) {
		assertEquals(numberOfMonthsBetweenDueDates, budgetLine.getPeriodBetweenDueDates().getMonths());
        assertEquals(beginDate, budgetLine.getBudgetInterval().getStart());
        assertEquals(endDate, budgetLine.getBudgetInterval().getEnd());
	}    
  
    public class YearlyFrequencyContext {

        @Before
        public void setUp(){
        	numberOfMonthsBetweenDueDates = 12;
        }

        @Test
        public void TestYearlyIncome () {
        	budgetLine = buildBudgetLine();
        	
            validateDates(budgetLine);
            
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
        public void testHalfYearlyIncome() {
        	budgetLine = buildBudgetLine();  
        	
            validateDates(budgetLine);
            
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
        public void testTrimesterIncome() {
        	budgetLine = buildBudgetLine();    
        	
            validateDates(budgetLine);
            
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
        public void testQuarterlyIncome() {
        	budgetLine = buildBudgetLine();    
        	
            validateDates(budgetLine);
            
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
        public void testMonthlyIncome() {
        	budgetLine = buildBudgetLine();  
        	
            validateDates(budgetLine);
            
            validateMonthlyBudgetLine(budgetLine);
        }


        private void validateMonthlyBudgetLine(BudgetLine budgetLine) {
            assertEquals(amount * 12, budgetLine.getBudgetAmountInInterval(new Interval(beginDate, endDate)), ACCURACY);
            assertEquals(amount, budgetLine.getMonthlyBudgetAmount(), ACCURACY);
            assertEquals(amount * 12, budgetLine.getYearlyBudgetAmount(), ACCURACY);
        }

    }

}
