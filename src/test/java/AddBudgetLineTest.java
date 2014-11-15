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

    private Category category;

    private BudgetLineTransactions addBudgetLineTransaction;

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
            assertEquals(budget * 12,  frequency.getYearlyBudget(), ACCURACY);
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
            assertEquals(budget,  frequency.getYearlyBudget(), ACCURACY);
        }

    }

    private void validateBudgetContent(BudgetLine budgetLine) {
        assertEquals(categoryId, budgetLine.getCategory().getId());
        assertEquals(categoryName, budgetLine.getCategory().getCategoryName());
        assertEquals(budgetLineName, budgetLine.getBudgetLineName());
    }

    private BudgetLine loadBudgetLine() {
        return BudgetDataBase.budgetDataBase.getBudgetLine(id);
    }

    private void addBudgetLine() {
        BudgetLineDTO budgetLineDTO = buildBudgetLineDTO();
        addBudgetLineTransaction.add(budgetLineDTO);
    }


    private BudgetLineDTO buildBudgetLineDTO() {
        return BudgetLineDTO.newBuilder()
                .withId(id)
                .withCategory(category)
                .withBudget(budget)
                .withBudgetLineName(budgetLineName)
                .build();
    }
}
