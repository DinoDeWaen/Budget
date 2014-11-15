/**
 * Created by dino on 07/11/14.
 */
public class AddMonthlyBudget extends AddBudget implements BudgetTransactions {

    @Override
    protected Frequency getBudgetLineFrequency(double budget) {
        return new MonthlyFrequency(budget);
    }
}
