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
public class AddBudgetLineRequestControllerTest {


    public static final double ACCURACY = 0.00001;
    private static final int id = 1;
    private static final String categoryName = "CategoryName";
    private static final String budgetLineName = "BudgetLineName";
    private static final double budget = 2000;

    private RequestBuilder requestBuilder;
    private UseCaseFactory useCaseFactory;

    public class MonthlyBudgetContext {
        @Before
        public void setUp() throws Exception {
            requestBuilder = new AddBudgetLineBuilder();
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
            requestBuilder = new AddBudgetLineBuilder();
            useCaseFactory = new AddYearlyBudgetLineFactory();
        }

        @Test
        public void addedBudgetLine_canBeRetrieved() {

            addBudgetLine();

            BudgetLine budgetLine = loadBudgetLine();
            validateBudgetContent(budgetLine);

            BudgetLineFrequency frequency = budgetLine.getBudgetLineFrequency();
            assertThat(frequency, instanceOf(BudgetLineYearlyFrequency.class));

            assertEquals(budgetLineName, budgetLine.getBudgetLineName());
            assertEquals(budget / 12, frequency.getMonthlyBudget(), ACCURACY);
        }

    }

    private void validateBudgetContent(BudgetLine budgetLine) {
        assertEquals(categoryName, budgetLine.getCategoryName());
        assertEquals(budgetLineName, budgetLine.getBudgetLineName());
    }

    private BudgetLine loadBudgetLine() {
        return BudgetDataBase.budgetDataBase.getBudgetLine(AddBudgetLineRequestControllerTest.id);
    }


    private void addBudgetLine() {
        Request addBudgetLineRequest = requestBuilder.createAddBudgetLineRequest(id, categoryName, budgetLineName, budget);
        UseCase useCase = useCaseFactory.CreateUseCase();
        useCase.Execute(addBudgetLineRequest);
    }
}
