import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by dino on 07/11/14.
 */
@RunWith(HierarchicalContextRunner.class)
public class AddBudgetLineTest {

    private static final double ACCURACY = 0.00001;
    private static final Integer id = 1;
    private static final String budgetLineName = "BudgetLineName";
    private static final double budget = 2000;

    private static final Integer categoryId = 3;
    private static final String categoryName = "CategoryName";

    private CategoryTransactions addCategoryTransaction;
    private CategoryDTO categoryDTO;

    private BudgetLineTransactions addBudgetLineTransaction;

    @Before
    public void setUp() throws Exception {
        addCategoryTransaction = new AddCategory();
        addCategory();
    }

    public class MonthlyBudgetContext {

        @Before
        public void setUp() throws Exception {
            addCategoryTransaction = new AddCategory();
            addBudgetLineTransaction = new AddMonthlyBudgetLine();
        }

        @Test
        public void addedBudgetLine_canBeRetrieved() {
            addBudgetLine();

            BudgetLine budgetLine = loadBudgetLine();
            validateBudgetContent(budgetLine);

            BudgetLineFrequency frequency = budgetLine.getBudgetLineFrequency();
            assertThat(frequency, instanceOf(BudgetLineMonthlyFrequency.class));
            assertEquals(budget, frequency.getMonthlyBudget(), ACCURACY);
        }

    }

    public class YearlyBudgetContext {

        @Before
        public void setUp() throws Exception {
            addBudgetLineTransaction = new AddYearlyBudgetLine();
        }

        @Test
        public void addedBudgetLine_canBeRetrieved() {

            addBudgetLine();

            BudgetLine budgetLine = loadBudgetLine();
            validateBudgetContent(budgetLine);

            BudgetLineFrequency frequency = budgetLine.getBudgetLineFrequency();
            assertThat(frequency, instanceOf(BudgetLineYearlyFrequency.class));
            assertEquals(budget / 12, frequency.getMonthlyBudget(), ACCURACY);
        }

    }

    private void addCategory() {
        categoryDTO = buildCategoryDTO();
        addCategoryTransaction.add(categoryDTO);
    }

    private CategoryDTO buildCategoryDTO() {
        return  CategoryDTO.newBuilder()
                .withCategoryId(categoryId)
                .withCategoryName(categoryName)
                .build();
    }

    private void validateBudgetContent(BudgetLine budgetLine) {
        assertEquals(categoryId, budgetLine.getCategoryId());
        assertEquals(budgetLineName, budgetLine.getBudgetLineName());
    }

    private BudgetLine loadBudgetLine() {
        Category category = DataBase.dataBase.getCategory(categoryId);
        return category.getBudgetLine(id);
    }

    private void addBudgetLine() {
        BudgetLineDTO budgetLineDTO = buildBudgetLineDTO();
        addBudgetLineTransaction.add(budgetLineDTO);
    }


    private BudgetLineDTO buildBudgetLineDTO() {
        return BudgetLineDTO.newBuilder()
                .withId(id)
                .withCategoryId(categoryId)
                .withBudget(budget)
                .withBudgetLineName(budgetLineName)
                .build();
    }
}
