import de.bechte.junit.runners.context.HierarchicalContextRunner;
import budget.BudgetLine;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import cashFlowTypes.CashFlowType;
import cashFlowTypes.Expense;
import cashFlowTypes.Income;



@RunWith(HierarchicalContextRunner.class)
public class BudgetLineTest {
    private static final double ACCURACY = 0.00001;
    private static final double amount = 2000;
    private static final DateTime beginDate = new DateTime(2014, 1, 1, 0, 0);
    private static final DateTime endDate = new DateTime(2015, 1, 1, 0, 0);
    private static final DateTime dueDate = new DateTime(2014, 1, 15, 0, 0);  
	
    private static int numberOfMonthsBetweenDueDates;  
    private static BudgetLine budgetLine;
    private static CashFlowType type;
    private static int sign;
    
    private BudgetLine buildIncomeBudgetLine() {
	    return BudgetLine.newBuilder()
	    		.withBudgetAmount(amount)
	    		.withBeginDate(beginDate)
	    		.withEndDate(endDate)
	    		.withDueDate(dueDate)   
	    		.withCashFlowType(type)
	            .withNumberOfMonthsBetweenDueDates(numberOfMonthsBetweenDueDates) 
	            .build();
    }
    
	private void validateDates(BudgetLine budgetLine) {
		assertEquals(numberOfMonthsBetweenDueDates, budgetLine.getPeriodBetweenDueDates().getMonths());
        assertEquals(beginDate, budgetLine.getBudgetInterval().getStart());
        assertEquals(endDate, budgetLine.getBudgetInterval().getEnd());
	}    
  
	private double addSignToAmount(double amount){
		return amount * sign;	
	}
    public class YearlyFrequencyContext {
        @Before
        public void setUp() {
        	numberOfMonthsBetweenDueDates = 12;
        }
        
        private void testYearlyFraquency (){
        	budgetLine = buildIncomeBudgetLine();
        	
            validateDates(budgetLine);
            
        	validateYearlyBudgetLine (budgetLine);
        	
        	validateYearlyCashFlow(budgetLine);	
        }
        
        private void validateYearlyBudgetLine(BudgetLine budgetLine) {
            assertEquals(amount, budgetLine.getBudgetAmountInInterval(new Interval(beginDate, endDate)), ACCURACY);	        	
            assertEquals(amount / 12, budgetLine.getMonthlyBudgetAmount(), ACCURACY);
            assertEquals(amount, budgetLine.getYearlyBudgetAmount(), ACCURACY);
        }
        
        private void validateYearlyCashFlow(BudgetLine budgetLine) {
            assertEquals(addSignToAmount(amount), budgetLine.getCashFlowAmountInInterval(new Interval(beginDate, endDate)), ACCURACY);	        	
            assertEquals(addSignToAmount(amount) / 12, budgetLine.getMonthlyCashFlowAmount(), ACCURACY);
            assertEquals(addSignToAmount(amount), budgetLine.getYearlyCashFlowAmount(), ACCURACY);
        }
        
        
        public class IncomeContext{
	        @Before
	        public void setUp(){
	        	type = new Income();
	        	sign = 1;
	        }
	
	        @Test
	        public void testYearlyIncome () {
	        	testYearlyFraquency ();
	        }
	

        }
        public class ExpenseContext{
	        @Before
	        public void setUp(){
	        	type = new Expense();
	        	sign = -1;
	        }
	
	        @Test
	        public void testYearlyExpense () {
	        	testYearlyFraquency ();
	        }
        }        
    }
    
    public class HalfYearlyFrequencyContext {
    	
        @Before
        public void setUp() {
        	numberOfMonthsBetweenDueDates = 6;
        }
        
        private void testHalfYearlyFrequency (){
        	budgetLine = buildIncomeBudgetLine();  
        	
            validateDates(budgetLine);
            
            validateHalfYearlyBudgetLine(budgetLine); 
            
            validateHalfYearlyCashFlow(budgetLine); 
        }
        
        private void validateHalfYearlyBudgetLine(BudgetLine budgetLine) {
            assertEquals(amount * 2, budgetLine.getBudgetAmountInInterval(new Interval(beginDate, endDate)), ACCURACY);
            assertEquals(amount / 6, budgetLine.getMonthlyBudgetAmount(), ACCURACY);
            assertEquals(amount * 2, budgetLine.getYearlyBudgetAmount(), ACCURACY);
        }
        
        private void validateHalfYearlyCashFlow(BudgetLine budgetLine) {
            assertEquals(addSignToAmount(amount) * 2, budgetLine.getCashFlowAmountInInterval(new Interval(beginDate, endDate)), ACCURACY);
            assertEquals(addSignToAmount(amount) / 6, budgetLine.getMonthlyCashFlowAmount(), ACCURACY);
            assertEquals(addSignToAmount(amount) * 2, budgetLine.getYearlyCashFlowAmount(), ACCURACY);
        }
        
        public class IncomeContext{
	        @Before
	        public void setUp(){
	        	type = new Income();
	        	sign = 1;
	        }
	
	        @Test
	        public void testHalfYearlyIncome () {
	        	testHalfYearlyFrequency ();
	        }
	

        }
        public class ExpenseContext{
	        @Before
	        public void setUp(){
	        	type = new Expense();
	        	sign = -1;
	        }
	
	        @Test
	        public void testHalfYearlyExpense () {
	        	testHalfYearlyFrequency ();
	        }
        }       

    }	
    
    public class TrimesterFrequencyContext {
    	
        @Before
        public void setUp() {
        	numberOfMonthsBetweenDueDates = 4;
        }

        
        private void testTrimesterFrequency() {
        	budgetLine = buildIncomeBudgetLine();    
        	
            validateDates(budgetLine);
            
            validateTrimesterBudgetLine(budgetLine);
            
            validateTrimesterCashFlow(budgetLine);
        }


        private void validateTrimesterBudgetLine(BudgetLine budgetLine) {
            assertEquals(amount * 3, budgetLine.getBudgetAmountInInterval(new Interval(beginDate, endDate)), ACCURACY);
            assertEquals(amount / 4, budgetLine.getMonthlyBudgetAmount(), ACCURACY);
            assertEquals(amount * 3, budgetLine.getYearlyBudgetAmount(), ACCURACY);          
        }
        
        private void validateTrimesterCashFlow(BudgetLine budgetLine) {        
	        assertEquals(addSignToAmount(amount) * 3, budgetLine.getCashFlowAmountInInterval(new Interval(beginDate, endDate)), ACCURACY);	        	
	        assertEquals(addSignToAmount(amount) / 4, budgetLine.getMonthlyCashFlowAmount(), ACCURACY);
	        assertEquals(addSignToAmount(amount) * 3, budgetLine.getYearlyCashFlowAmount(), ACCURACY); 
        }
        
        public class IncomeContext{
	        @Before
	        public void setUp(){
	        	type = new Income();
	        	sign = 1;
	        }
	
	        @Test
	        public void testTrimesterIncome () {
	        	testTrimesterFrequency ();
	        }
	

        }
        public class ExpenseContext{
	        @Before
	        public void setUp(){
	        	type = new Expense();
	        	sign = -1;
	        }
	
	        @Test
	        public void testTrimesterExpense () {
	        	testTrimesterFrequency ();
	        }
        } 

    }
    
    public class QuarterlyFrequencyContext {
    	
        @Before
        public void setUp() {
        	numberOfMonthsBetweenDueDates = 3;
        }

        private void testQuarterlyFrequency() {
        	budgetLine = buildIncomeBudgetLine();    
        	
            validateDates(budgetLine);
            
            validateQuarterlyBudgetLine(budgetLine);
            
            validateQuarterlyCashFlow(budgetLine);            
        }


        private void validateQuarterlyBudgetLine(BudgetLine budgetLine) {
            assertEquals(amount * 4, budgetLine.getBudgetAmountInInterval(new Interval(beginDate, endDate)), ACCURACY);
            assertEquals(amount / 3, budgetLine.getMonthlyBudgetAmount(), ACCURACY);
            assertEquals(amount * 4, budgetLine.getYearlyBudgetAmount(), ACCURACY);
        } 
        
        private void validateQuarterlyCashFlow(BudgetLine budgetLine) {
	        assertEquals(addSignToAmount(amount) * 4, budgetLine.getCashFlowAmountInInterval(new Interval(beginDate, endDate)), ACCURACY);	        	
	        assertEquals(addSignToAmount(amount) / 3, budgetLine.getMonthlyCashFlowAmount(), ACCURACY);
	        assertEquals(addSignToAmount(amount) * 4, budgetLine.getYearlyCashFlowAmount(), ACCURACY);
        }
        
        public class IncomeContext{
	        @Before
	        public void setUp(){
	        	type = new Income();
	        	sign = 1;
	        }
	
	        @Test
	        public void testQuarterlyIncome () {
	        	testQuarterlyFrequency ();
	        }
	

        }
        public class ExpenseContext{
	        @Before
	        public void setUp(){
	        	type = new Expense();
	        	sign = -1;
	        }
	
	        @Test
	        public void testQuarterlyExpense () {
	        	testQuarterlyFrequency ();
	        }
        } 
    }	    
    
    public class MonthlyBudgetContext {

        @Before
        public void setUp() throws Exception {
        	numberOfMonthsBetweenDueDates = 1;
        }

        private void testMonthlyFrequency() {
        	budgetLine = buildIncomeBudgetLine();  
        	
            validateDates(budgetLine);
            
            validateMonthlyBudgetLine(budgetLine);
            
            validateMonthlyCashFlow(budgetLine); 
        }


        private void validateMonthlyBudgetLine(BudgetLine budgetLine) {
            assertEquals(amount * 12, budgetLine.getBudgetAmountInInterval(new Interval(beginDate, endDate)), ACCURACY);
            assertEquals(amount, budgetLine.getMonthlyBudgetAmount(), ACCURACY);
            assertEquals(amount * 12, budgetLine.getYearlyBudgetAmount(), ACCURACY);
        }
        private void validateMonthlyCashFlow(BudgetLine budgetLine) {
	        assertEquals(addSignToAmount(amount) * 12, budgetLine.getCashFlowAmountInInterval(new Interval(beginDate, endDate)), ACCURACY);	        	
	        assertEquals(addSignToAmount(amount), budgetLine.getMonthlyCashFlowAmount(), ACCURACY);
	        assertEquals(addSignToAmount(amount) * 12, budgetLine.getYearlyCashFlowAmount(), ACCURACY);
        }
        public class IncomeContext{
	        @Before
	        public void setUp(){
	        	type = new Income();
	        	sign = 1;
	        }
	
	        @Test
	        public void testMonthlyIncome () {
	        	testMonthlyFrequency ();
	        }
	

        }
        public class ExpenseContext{
	        @Before
	        public void setUp(){
	        	type = new Expense();
	        	sign = -1;
	        }
	
	        @Test
	        public void testMonthlyExpense () {
	        	testMonthlyFrequency ();
	        }
        } 
    }

}
