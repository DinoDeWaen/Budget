import de.bechte.junit.runners.context.HierarchicalContextRunner;
import dto.BudgetDTO;
import model.Budget;
import model.Category;
import model.Frequency;
import model.MonthlyFrequency;
import model.YearlyFrequency;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import services.AddMonthlyBudgetService;
import services.AddYearlyBudgetService;
import services.BudgetServices;
import util.BudgetDataBase;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

/**
 * Created by dino on 07/11/14.
 */
@RunWith(HierarchicalContextRunner.class)
public class AddBudgetTest {

    private static final double ACCURACY = 0.00001;
    private static final String name = "BudgetLineName";
    private static final double amount = 2000;

    private static final Integer categoryId = 3;
    private static final String categoryName = "CategoryName";

    private Category category;

    private BudgetServices addBudgetTransaction;

    public  AddBudgetTest() {
        category = Category.newBuilder()
                .withCategoryName(categoryName)
                .withCategoryId(categoryId)
                .build();
    }

    public class MonthlyBudgetContext {

        @Before
        public void setUp() throws Exception {
            addBudgetTransaction = new AddMonthlyBudgetService();
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

            Frequency frequency = budget.getFrequency();
            assertThat(frequency, instanceOf(MonthlyFrequency.class));
            Assert.assertEquals(amount, frequency.getMonthlyAmount(), ACCURACY);
            Assert.assertEquals(amount * 12, frequency.getYearlyAmount(), ACCURACY);
        }

    }


    public class YearlyBudgetContext {

        @Before
        public void setUp() throws Exception {
            addBudgetTransaction = new AddYearlyBudgetService();
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

            Frequency frequency = budget.getFrequency();
            assertThat(frequency, instanceOf(YearlyFrequency.class));
            Assert.assertEquals(amount / 12, frequency.getMonthlyAmount(), ACCURACY);
            Assert.assertEquals(amount, frequency.getYearlyAmount(), ACCURACY);
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
                .withCategory(category)
                .withAmount(amount)
                .withName(name)
                .build();
    }

    private BudgetDTO buildBudgetLineWithoutCategoryDTO() {
        return BudgetDTO.newBuilder()
                .withAmount(amount)
                .withName(name)
                .build();
    }

    private Budget loadBudget(Integer id) {
        return BudgetDataBase.budgetDataBase.getBudget(id);
    }

    private void validateBudgetContent(Budget budget) {
        assertEquals(name, budget.getName());
    }

    private void validateCategoryContent(Budget budget) {
        assertNotNull("category is null", budget.getCategory());

        assertEquals(categoryId, budget.getCategory().getId());
        assertEquals(categoryName, budget.getCategory().getName());
    }

    private void validateEmptyCategoryContent(Budget budget) {
        assertNotNull("category is null", budget.getCategory());

        assertEquals(Category.emptyCategory.getId(), budget.getCategory().getId());
        assertEquals(Category.emptyCategory.getName(), budget.getCategory().getName());
    }
}
