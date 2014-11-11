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
public class AddBudgetLineRequestTest {

    private static final double ACCURACY = 0.00001;
    private static final Integer id = 1;
    private static final Integer categoryId = 3;
    private static final String budgetLineName = "BudgetLineName";
    private static final double budget = 2000;

    private UseCaseFactory useCaseFactory;

    public class MonthlyBudgetContext {

        @Before
        public void setUp() throws Exception {
            useCaseFactory = new AddMonthlyBudgetLineFactory();
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
            useCaseFactory = new AddYearlyBudgetLineFactory();
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

    private void validateBudgetContent(BudgetLine budgetLine) {
        assertEquals(categoryId, budgetLine.getCategoryId());
        assertEquals(budgetLineName, budgetLine.getBudgetLineName());
    }

    private BudgetLine loadBudgetLine() {
        return BudgetDataBase.budgetDataBase.getBudgetLine(AddBudgetLineRequestTest.id);
    }

    private void addBudgetLine() {
        Request addBudgetLineRequest = buildBudgetLineBuilder();
        UseCase useCase = useCaseFactory.createUseCase();
        useCase.execute(addBudgetLineRequest);
    }


    private AddBudgetLineRequest buildBudgetLineBuilder() {
        return AddBudgetLineRequest.newBuilder()
                .withId(id)
                .withCategoryId(categoryId)
                .withBudget(budget)
                .withBudgetLineName(budgetLineName)
                .build();
    }
}