import gateway.BudgetDataBase;
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
import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * Created by Dino on 07/11/14.
 */
@RunWith(HierarchicalContextRunner.class)
public class AddBudgetServiceTest {

    private static final double ACCURACY = 0.00001;
    private static final String name = "BudgetLineName";
    private static final double amount = 2000;
    private static final DateTime beginDate = new DateTime(2014, 1, 1, 0, 0);
    private static final DateTime endDate = new DateTime(2015, 1, 1, 0, 0);
    private static final DateTime dueDate = new DateTime(2014, 1, 15, 0, 0);    
    
    private static final Integer numberOfMonthsBetweenDueDates = 1;   
    

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
    
    private Budget loadBudget(Integer id) {
        return BudgetDataBase.budgetDataBase.getBudget(id);
    }
    
    private Integer addBudget() {
        BudgetDTO budgetDTO = buildBudgetDTO();
        return addBudgetService.addBudget(budgetDTO);
    }

    private BudgetDTO buildBudgetDTO() {
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
    
    public class IncomeExpenseContext{
	    @Test
	    public void addedIncomeBudget_canBeRetrieved() {
	        Integer id = addBudget();
	
	        Budget budget = loadBudget(id);
	
	        validateBudgetContent(budget);
	    }    
	
	    private void validateBudgetContent(Budget budget) {
	        assertEquals(name, budget.getName());
	        assertEquals(beginDate, budget.getBudgetInterval().getStart());
	        assertEquals(endDate, budget.getBudgetInterval().getEnd());
	        assertEquals(dueDate, budget.getDueDate()); 
	        
            assertEquals(amount * 12, budget.getBudgetAmount(new Interval(beginDate, endDate)), ACCURACY);
            assertEquals(amount, budget.getMonthlyBudgetAmount(), ACCURACY);
            assertEquals(amount * 12, budget.getYearlyBudgetAmount(), ACCURACY);
	    }
    }
    
    public class CategoryContext
    {
    	public class EmptyCategoryContext
    	{
	        @Test
	        public void addedBudgetWithoutCategory_canBeRetrieved() {
	            Integer id = addBudgetWithoutCategory();
	
	            Budget budget = loadBudget(id);
	
	            validateEmptyCategoryContent(budget);
	        }
	        
	        private Integer addBudgetWithoutCategory() {
	            BudgetDTO budgetDTO = buildBudgetWithoutCategoryDTO();
	            return addBudgetService.addBudget(budgetDTO);
	        }
	    	
	        private BudgetDTO buildBudgetWithoutCategoryDTO() {
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
	            Integer id = addBudget();
	
	            Budget budget = loadBudget(id);
	
	            validateCategoryContent(budget);
	        }  	
	    	
	        private void validateCategoryContent(Budget budget) {
	            assertNotNull("category is null", budget.getCategory());

	            assertEquals(categoryName, budget.getCategory().getName());
	        }	
    	}    	
    }
}
