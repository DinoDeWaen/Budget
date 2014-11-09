/**
 * Created by dino on 08/11/14.
 */
public class BudgetLineYearlyFrequency extends BudgetLineFrequency {
    public BudgetLineYearlyFrequency(double budget) {
        super(budget);
    }

    @Override
    public double getMonthlyBudget() {
        return budget /12;
    }
}
