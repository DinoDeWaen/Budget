package budgetModel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import gateway.BudgetDataBase;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import budget.AddBudgetService;
import budget.BudgetCenter;
import budget.BudgetDTO;
import budget.BudgetServices;
import category.Category;
import de.bechte.junit.runners.context.HierarchicalContextRunner;

/**
 * Created by Dino on 07/11/14.
 */
@RunWith(HierarchicalContextRunner.class)
public class AddBudgetServiceTest {

    private static final double ACCURACY = 0.00001;
    private static final double amount = 2000;    
    private static final String name = "BudgetLineName";
    private static final DateTime beginDate = new DateTime(2014, 1, 1, 0, 0);
    private static final DateTime endDate = new DateTime(2015, 1, 1, 0, 0);
    private static final DateTime dueDate = new DateTime(2014, 1, 15, 0, 0);    
    
    private static final int numberOfMonthsBetweenDueDates = 1;   
    

    private static Integer categoryId;
    private static final String categoryName = "CategoryName";

    private static final Category category = Category.newBuilder().withCategoryName(categoryName).build();

    private BudgetServices addBudgetService;

    public  AddBudgetServiceTest() {

    }
    
    @Before
    public void setUp () throws Exception{
        addBudgetService = new AddBudgetService();

        categoryId = BudgetDataBase.getDB().addCategory(category);
    }
    
    private BudgetCenter loadBudget(Integer id) {
        return BudgetDataBase.getDB().getBudget(id);
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
	
	        BudgetCenter budget = loadBudget(id);
	
	        validateBudgetContent(budget);
	    }    
	
	    private void validateBudgetContent(BudgetCenter budget) {
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
	
	            BudgetCenter budget = loadBudget(id);
	
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
	    	
	        private void validateEmptyCategoryContent(BudgetCenter budget) {
	            assertNotNull("category is null", budget.getCategory());
	
	            assertEquals(Category.emptyCategory.getName(), budget.getCategory().getName());
	        } 	
    	}
    	
    	public class FilledCategoryContext
    	{
	        @Test
	        public void addedBudgetWithCategory_canBeRetrieved() {
	            Integer id = addBudget();
	
	            BudgetCenter budget = loadBudget(id);
	
	            validateCategoryContent(budget);
	        }  	
	    	
	        private void validateCategoryContent(BudgetCenter budget) {
	            assertNotNull("category is null", budget.getCategory());

	            assertEquals(categoryName, budget.getCategory().getName());
	        }	
    	}    	
    }
    
    public class AddMultipleCashFlowContext{
    	private final double [] incomeAmounts = {2000.00, 99.9, 200.34, 43.98};
    	private final double [] expenseAmounts = {1000.00, 69.9,20.34, 96.02};
    	
    	
    	@Test
    	public void testSumOffCashFlows(){
			 Integer id = addBudget();
				
			 BudgetCenter budget = loadBudget(id);
			 
			 Utilities.addCashFlows(budget, incomeAmounts, expenseAmounts);	 
			 
			 assertEquals(Arrays.stream(incomeAmounts).sum() - Arrays.stream(expenseAmounts).sum(), budget.calculateCashFlowBalance(), ACCURACY);
    	}
    	
    }
    
}
