/**
 * Created by dino on 08/11/14.
 */
public class AddYearlyBudget extends AddBudget implements BudgetTransactions {

    @Override
    protected Frequency getBudgetLineFrequency(double budget) {
        return new YearlyFrequency(budget);
    }
}
