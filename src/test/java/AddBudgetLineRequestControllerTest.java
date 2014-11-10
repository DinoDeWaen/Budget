import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

/**
 * Created by dino on 07/11/14.
 */
public class AddBudgetLineRequestControllerTest {

    private RequestBuilder requestBuilder;
    private UseCaseFactory useCaseFactory;

    public static final double ACCURACY = 0.00001;

    private final int id = 1;
    private final String categoryName = "CategoryName";
    private final String budgetLineName = "BudgetLineName";
    private final double budget = 2000;


    @Before
    public void setUp() throws Exception {
        requestBuilder = new AddBudgetLineBuilder();
        useCaseFactory = new AddMonthlyBudgetLineFactory();
    }

    @Test
    public void addMonthlyBudgetLine() {


        addBudgetLine();

        BudgetLine budgetLine = BudgetDataBase.budgetDataBase.getBudgetLine(id);
        assertEquals(categoryName, budgetLine.getCategoryName());
        assertEquals(budgetLineName, budgetLine.getBudgetLineName());

        BudgetLineFrequency frequency = budgetLine.getBudgetLineFrequency();
        assertThat(frequency, instanceOf(BudgetLineMonthlyFrequency.class));
        assertEquals(budget, frequency.getMonthlyBudget(), ACCURACY);
    }

    @Test
    public void addYearlyBudgetLine() {

        addBudgetLine();

        BudgetLine budgetLine = BudgetDataBase.budgetDataBase.getBudgetLine(id);
        assertEquals(categoryName, budgetLine.getCategoryName());
        assertEquals(budgetLineName, budgetLine.getBudgetLineName());

        BudgetLineFrequency frequency = budgetLine.getBudgetLineFrequency();
        assertThat(frequency, instanceOf(BudgetLineYearlyFrequency.class));

        assertEquals(budgetLineName, budgetLine.getBudgetLineName());
        assertEquals(budget / 12, frequency.getMonthlyBudget(), ACCURACY);
    }

    private void addBudgetLine() {
        Request request = requestBuilder.createAddBudgetLineRequest(id, categoryName, budgetLineName, budget);
        UseCase useCase = useCaseFactory.CreateUseCase();
        useCase.Execute(request);
    }
}
