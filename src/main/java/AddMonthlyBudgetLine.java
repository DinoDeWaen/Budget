/**
 * Created by dino on 07/11/14.
 */
public class AddMonthlyBudgetLine extends AddBudgetLine implements BudgetLineTransactions {

    @Override
    protected BudgetLineFrequency getBudgetLineFrequency(double budget) {
        return new BudgetLineMonthlyFrequency (budget);
    }
}
