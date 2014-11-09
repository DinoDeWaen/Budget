/**
 * Created by dino on 08/11/14.
 */
public class AddYearlyBudgetLineUseCase extends AddBudgetLineUseCase implements UseCase {

    @Override
    protected BudgetLineFrequency getBudgetLineFrequency(double budget) {
        return new BudgetLineYearlyFrequency (budget);
    }
}
