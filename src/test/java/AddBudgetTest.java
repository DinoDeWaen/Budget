import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

/**
 * Created by dino on 07/11/14.
 */
@RunWith(HierarchicalContextRunner.class)
public class AddBudgetTest {

    private static final double ACCURACY = 0.00001;
    private static final Integer id = 1;
    private static final String name = "BudgetLineName";
    private static final double amount = 2000;

    private static final Integer categoryId = 3;
    private static final String categoryName = "CategoryName";

    private Category category;

    private BudgetTransactions addBudgetTransaction;

    @Before
    public void setUp() throws Exception {
        category = Category.newBuilder()
                           .withCategoryName(categoryName)
                           .withCategoryId(categoryId)
                           .build();
    }

    public class MonthlyBudgetContext {

        @Before
        public void setUp() throws Exception {
            addBudgetTransaction = new AddMonthlyBudget();
        }

        @Test
        public void addedBudgetLine_canBeRetrieved() {
            addBudgetLine();

            Budget budget = loadBudget();

            validateMonthlyBudgetLine(budget);

            validateCategoryContent(budget);
        }

        @Test
        public void addedBudgetLineWithoutCategory_canBeRetrieved() {
            addBudgetLineWithoutCategory();

            Budget budget = loadBudget();

            validateMonthlyBudgetLine(budget);

            validateEmptyCategoryContent(budget);
        }
    }

    private void validateMonthlyBudgetLine(Budget budget) {

        validateBudgetContent(budget);

        Frequency frequency = budget.getFrequency();
        assertThat(frequency, instanceOf(MonthlyFrequency.class));
        Assert.assertEquals(amount, frequency.getMonthlyAmount(), ACCURACY);
        Assert.assertEquals(amount * 12, frequency.getYearlyAmount(), ACCURACY);
    }

    public class YearlyBudgetContext {

        @Before
        public void setUp() throws Exception {
            addBudgetTransaction = new AddYearlyBudget();
        }

        @Test
        public void addedBudgetLine_canBeRetrieved() {

            addBudgetLine();

            Budget budget = loadBudget();

            validateYearlyBudgetLine(budget);

            validateCategoryContent(budget);
        }

        @Test
        public void addedBudgetLineWithoutCategory_canBeRetrieved() {

            addBudgetLineWithoutCategory();

            Budget budget = loadBudget();

            validateYearlyBudgetLine(budget);

            validateEmptyCategoryContent(budget);
        }

        private void validateYearlyBudgetLine(Budget budget) {
            validateBudgetContent(budget);

            Frequency frequency = budget.getFrequency();
            assertThat(frequency, instanceOf(YearlyFrequency.class));
            Assert.assertEquals(amount/ 12, frequency.getMonthlyAmount(), ACCURACY);
            Assert.assertEquals(amount, frequency.getYearlyAmount(), ACCURACY);
        }

    }
    private void addBudgetLine() {
        BudgetDTO budgetDTO = buildBudgetLineDTO();
        addBudgetTransaction.addBudget(budgetDTO);
    }

    private void addBudgetLineWithoutCategory() {
        BudgetDTO budgetDTO = buildBudgetLineWithoutCategoryDTO();
        addBudgetTransaction.addBudget(budgetDTO);
    }

    private BudgetDTO buildBudgetLineDTO() {
        return BudgetDTO.newBuilder()
                .withId(id)
                .withCategory(category)
                .withAmount(amount)
                .withName(name)
                .build();
    }

    private BudgetDTO buildBudgetLineWithoutCategoryDTO() {
        return BudgetDTO.newBuilder()
                .withId(id)
                .withAmount(amount)
                .withName(name)
                .build();
    }

    private Budget loadBudget() {
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
        assertEquals(Category.emptyCategory.getName(), budget.getCategory().getName() );
    }
}
