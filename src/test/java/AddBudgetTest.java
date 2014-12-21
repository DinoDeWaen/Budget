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

/**
 * Created by dino on 07/11/14.
 */
@RunWith(HierarchicalContextRunner.class)
public class AddBudgetTest {

    private static final double ACCURACY = 0.00001;
    private static final String name = "BudgetLineName";
    private static final double amount = 2000;
    private static final DateTime beginDate = new DateTime(2014, 1, 1, 0, 0);
    private static final DateTime endDate = new DateTime(2015, 1, 1, 0, 0);
    private static final DateTime dueDate = new DateTime(2014, 1, 15, 0, 0);    
    
    private static Integer paymentFrequency;   
    

    private static Integer categoryId;
    private static final String categoryName = "CategoryName";

    private Category category;

    private BudgetServices addBudgetTransaction;

    public  AddBudgetTest() {
        category = Category.newBuilder()
                .withCategoryName(categoryName)
                .build();
        categoryId = BudgetDataBase.budgetDataBase.addCategory(category);
    }

    public class MonthlyBudgetContext {

        @Before
        public void setUp() throws Exception {
            addBudgetTransaction = new AddBudgetService();
        	paymentFrequency = 12;
        }

        @Test
        public void addedBudgetLine_canBeRetrieved() {
            Integer id = addBudgetLine();

            Budget budget = loadBudget(id);

            validateMonthlyBudgetLine(budget);

            validateCategoryContent(budget);
        }

        @Test
        public void addedBudgetLineWithoutCategory_canBeRetrieved() {
            Integer id = addBudgetLineWithoutCategory();

            Budget budget = loadBudget(id);

            validateMonthlyBudgetLine(budget);

            validateEmptyCategoryContent(budget);
        }

        private void validateMonthlyBudgetLine(Budget budget) {

            validateBudgetContent(budget);

            Assert.assertEquals(amount, budget.getMonthlyBudgetAmount(), ACCURACY);
            Assert.assertEquals(amount * 12, budget.getYearlyBudgetAmount(), ACCURACY);
        }

    }


    public class YearlyBudgetContext {

        @Before
        public void setUp() throws Exception {
            addBudgetTransaction = new AddBudgetService();
        	paymentFrequency = 1;
        }

        @Test
        public void addedBudgetLine_canBeRetrieved() {

            Integer id = addBudgetLine();

            Budget budget = loadBudget(id);

            validateYearlyBudgetLine(budget);

            validateCategoryContent(budget);
        }

        @Test
        public void addedBudgetLineWithoutCategory_canBeRetrieved() {

            Integer id = addBudgetLineWithoutCategory();

            Budget budget = loadBudget(id);

            validateYearlyBudgetLine(budget);

            validateEmptyCategoryContent(budget);
        }

        private void validateYearlyBudgetLine(Budget budget) {
            validateBudgetContent(budget);
            Assert.assertEquals(amount / 12, budget.getMonthlyBudgetAmount(), ACCURACY);
            Assert.assertEquals(amount, budget.getYearlyBudgetAmount(), ACCURACY);
        }

    }

    private Integer addBudgetLine() {
        BudgetDTO budgetDTO = buildBudgetLineDTO();
        return addBudgetTransaction.addBudget(budgetDTO);
    }

    private Integer addBudgetLineWithoutCategory() {
        BudgetDTO budgetDTO = buildBudgetLineWithoutCategoryDTO();
        return addBudgetTransaction.addBudget(budgetDTO);
    }

    private BudgetDTO buildBudgetLineDTO() {
        return BudgetDTO.newBuilder()
                .withName(name)        		
                .withAmount(amount)
        		.withBeginDate(beginDate)
        		.withEndDate(endDate)
        		.withDueDate(dueDate)                
                .withPaymentFrequency(paymentFrequency)
        		.withCategory(categoryId)
                .build();
    }

    private BudgetDTO buildBudgetLineWithoutCategoryDTO() {
        return BudgetDTO.newBuilder()
        		.withBeginDate(beginDate)
        		.withEndDate(endDate)
        		.withDueDate(dueDate)
                .withAmount(amount)
                .withName(name)
                .withPaymentFrequency(paymentFrequency)
                .build();
    }

    private Budget loadBudget(Integer id) {
        return BudgetDataBase.budgetDataBase.getBudget(id);
    }

    private void validateBudgetContent(Budget budget) {
        assertEquals(name, budget.getName());
        assertEquals(beginDate, budget.getBeginDate());
        assertEquals(endDate, budget.getEndDate());
        assertEquals(dueDate, budget.getDueDate());
        Assert.assertEquals(paymentFrequency, budget.getPaymentFrequency());        
    }

    private void validateCategoryContent(Budget budget) {
        assertNotNull("category is null", budget.getCategory());

        assertEquals(categoryName, budget.getCategory().getName());
    }

    private void validateEmptyCategoryContent(Budget budget) {
        assertNotNull("category is null", budget.getCategory());

        assertEquals(Category.emptyCategory.getName(), budget.getCategory().getName());
    }
}
