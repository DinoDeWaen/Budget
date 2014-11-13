/**
 * Created by dino on 08/11/14.
 */
public class AddYearlyBudgetLine extends AddBudgetLine implements BudgetLineTransactions {

    @Override
    protected BudgetLineFrequency getBudgetLineFrequency(double budget) {
        return new BudgetLineYearlyFrequency (budget);
    }
}
