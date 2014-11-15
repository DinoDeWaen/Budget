/**
 * Created by dino on 08/11/14.
 */
public class BudgetLineMonthlyFrequency extends BudgetLineFrequency {
    public BudgetLineMonthlyFrequency(double budget) {
        super(budget);
    }

    @Override
    public double getMonthlyBudget() {
        return budget;
    }

    @Override
    public double getYearlyBudget() {
        return budget * 12;
    }
}
