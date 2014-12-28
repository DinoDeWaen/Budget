import de.bechte.junit.runners.context.HierarchicalContextRunner;
import budget.BudgetDTO;
import budget.Budget;
import category.Category;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import budget.AddBudgetService;
import budget.BudgetServices;
import util.BudgetDataBase;
import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * Created by dino on 07/11/14.
 */
@RunWith(HierarchicalContextRunner.class)
public class AddBudgetServiceTest {

    private static final double ACCURACY = 0.00001;
    private static final String name = "BudgetLineName";
    private static final double amount = 2000;
    private static final DateTime beginDate = new DateTime(2014, 1, 1, 0, 0);
    private static final DateTime endDate = new DateTime(2015, 1, 1, 0, 0);
    private static final DateTime dueDate = new DateTime(2014, 1, 15, 0, 0);    
    
    private static Integer numberOfMonthsBetweenDueDates;   
    

    private static Integer categoryId;
    private static final String categoryName = "CategoryName";

    private Category category;

    private BudgetServices addBudgetService;

    public  AddBudgetServiceTest() {

    }
    
    @Before
    public void setUp () throws Exception{
        addBudgetService = new AddBudgetService();
        
        category = Category.newBuilder()
                .withCategoryName(categoryName)
                .build();
        categoryId = BudgetDataBase.budgetDataBase.addCategory(category);
    }
    
    @Test
    public void addedBudget_canBeRetrieved() {
        Integer id = addBudgetLine();

        Budget budget = loadBudget(id);

        validateBudgetContent(budget);
    }    

    private Integer addBudgetLine() {
        BudgetDTO budgetDTO = buildBudgetLineDTO();
        return addBudgetService.addBudget(budgetDTO);
    }

    private BudgetDTO buildBudgetLineDTO() {
        return BudgetDTO.newBuilder()
                .withName(name)        		
                .withAmount(amount)
        		.withBeginDate(beginDate)
        		.withEndDate(endDate)
        		.withDueDate(dueDate)  
        		.withNumberOfMonthsBetweenDueDates(numberOfMonthsBetweenDueDates)
        		.withCategory(categoryId)
                .build();
    }
   

    private Budget loadBudget(Integer id) {
        return BudgetDataBase.budgetDataBase.getBudget(id);
    }

    private void validateBudgetContent(Budget budget) {
        assertEquals(name, budget.getName());
        assertEquals(beginDate, budget.getBudgetInterval().getStart());
        assertEquals(endDate, budget.getBudgetInterval().getEnd());
        assertEquals(dueDate, budget.getDueDate());       
    }
    
    public class CategoryContext
    {
    	public class EmptyCategoryContext
    	{
	        @Test
	        public void addedBudgetWithoutCategory_canBeRetrieved() {
	            Integer id = addBudgetLineWithoutCategory();
	
	            Budget budget = loadBudget(id);
	
	            validateEmptyCategoryContent(budget);
	        }
	        
	        private Integer addBudgetLineWithoutCategory() {
	            BudgetDTO budgetDTO = buildBudgetLineWithoutCategoryDTO();
	            return addBudgetService.addBudget(budgetDTO);
	        }
	    	
	        private BudgetDTO buildBudgetLineWithoutCategoryDTO() {
	            return BudgetDTO.newBuilder()
	            		.withBeginDate(beginDate)
	            		.withEndDate(endDate)
	            		.withDueDate(dueDate)
	                    .withAmount(amount)
	                    .withName(name)
	                    .build();
	        }    	
	    	
	        private void validateEmptyCategoryContent(Budget budget) {
	            assertNotNull("category is null", budget.getCategory());
	
	            assertEquals(Category.emptyCategory.getName(), budget.getCategory().getName());
	        } 	
    	}
    	
    	public class FilledCategoryContext
    	{
	        @Test
	        public void addedBudgetWithCategory_canBeRetrieved() {
	            Integer id = addBudgetLine();
	
	            Budget budget = loadBudget(id);
	
	            validateCategoryContent(budget);
	        }  	
	    	
	        private void validateCategoryContent(Budget budget) {
	            assertNotNull("category is null", budget.getCategory());

	            assertEquals(categoryName, budget.getCategory().getName());
	        }	
    	}    	
    }
    
	public class FrequencyContext
	{   
	    public class YearlyFrequencyContext {
	
	        @Before
	        public void setUp(){
	        	numberOfMonthsBetweenDueDates = 12;
	        }
	
	        @Test
	        public void addedBudgetLine_canBeRetrieved() {
	
	            Integer id = addBudgetLine();
	
	            Budget budget = loadBudget(id);
	            
	            validateYearlyBudgetLine(budget);
	        }
	
	        private void validateYearlyBudgetLine(Budget budget) {
	            Assert.assertEquals(amount, budget.getBudgetAmount(new Interval(beginDate, endDate)), ACCURACY);	        	
	            Assert.assertEquals(amount / 12, budget.getMonthlyBudgetAmount(), ACCURACY);
	            Assert.assertEquals(amount, budget.getYearlyBudgetAmount(), ACCURACY);
	        }
	
	    }
	    
	    public class HalfYearlyFrequencyContext {
	    	
	        @Before
	        public void setUp() {
	        	numberOfMonthsBetweenDueDates = 6;
	        }
	
	        @Test
	        public void addedBudgetLine_canBeRetrieved() {
	            Integer id = addBudgetLine();
	
	            Budget budget = loadBudget(id);         
	            
	            validateHalfYearlyBudgetLine(budget);
	        }
	
	
	        private void validateHalfYearlyBudgetLine(Budget budget) {
	            Assert.assertEquals(amount * 2, budget.getBudgetAmount(new Interval(beginDate, endDate)), ACCURACY);
	            Assert.assertEquals(amount / 6, budget.getMonthlyBudgetAmount(), ACCURACY);
	            Assert.assertEquals(amount * 2, budget.getYearlyBudgetAmount(), ACCURACY);
	        }
	
	    }	
	    
	    public class TrimesterFrequencyContext {
	    	
	        @Before
	        public void setUp() {
	        	numberOfMonthsBetweenDueDates = 4;
	        }
	
	        @Test
	        public void addedBudgetLine_canBeRetrieved() {
	            Integer id = addBudgetLine();
	
	            Budget budget = loadBudget(id);         
	            
	            validateHalfYearlyBudgetLine(budget);
	        }
	
	
	        private void validateHalfYearlyBudgetLine(Budget budget) {
	            Assert.assertEquals(amount * 3, budget.getBudgetAmount(new Interval(beginDate, endDate)), ACCURACY);
	            Assert.assertEquals(amount / 4, budget.getMonthlyBudgetAmount(), ACCURACY);
	            Assert.assertEquals(amount * 3, budget.getYearlyBudgetAmount(), ACCURACY);
	        }
	
	    }
	    
	    public class QuarterlyFrequencyContext {
	    	
	        @Before
	        public void setUp() {
	        	numberOfMonthsBetweenDueDates = 3;
	        }
	
	        @Test
	        public void addedBudgetLine_canBeRetrieved() {
	            Integer id = addBudgetLine();
	
	            Budget budget = loadBudget(id);         
	            
	            validateHalfYearlyBudgetLine(budget);
	        }
	
	
	        private void validateHalfYearlyBudgetLine(Budget budget) {
	            Assert.assertEquals(amount * 4, budget.getBudgetAmount(new Interval(beginDate, endDate)), ACCURACY);
	            Assert.assertEquals(amount / 3, budget.getMonthlyBudgetAmount(), ACCURACY);
	            Assert.assertEquals(amount * 4, budget.getYearlyBudgetAmount(), ACCURACY);
	        }
	
	    }	    
	    
	    public class MonthlyBudgetContext {
	
	        @Before
	        public void setUp() throws Exception {
	        	numberOfMonthsBetweenDueDates = 1;
	        }
	
	        @Test
	        public void addedBudgetLine_canBeRetrieved() {
	            Integer id = addBudgetLine();
	
	            Budget budget = loadBudget(id);         
	            
	            validateMonthlyBudgetLine(budget);
	        }
	
	
	        private void validateMonthlyBudgetLine(Budget budget) {
	            Assert.assertEquals(amount * 12, budget.getBudgetAmount(new Interval(beginDate, endDate)), ACCURACY);
	            Assert.assertEquals(amount, budget.getMonthlyBudgetAmount(), ACCURACY);
	            Assert.assertEquals(amount * 12, budget.getYearlyBudgetAmount(), ACCURACY);
	        }
	
	    }
	}
}
