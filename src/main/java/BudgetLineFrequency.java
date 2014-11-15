/**
 * Created by dino on 08/11/14.
 */
public abstract class BudgetLineFrequency {
    protected double budget;

    protected BudgetLineFrequency(double budget) {
        this.budget = budget;
    }
    public abstract double getMonthlyBudget();
    public abstract double getYearlyBudget();
}
