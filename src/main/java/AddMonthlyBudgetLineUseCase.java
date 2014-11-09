/**
 * Created by dino on 07/11/14.
 */
public class AddMonthlyBudgetLineUseCase extends AddBudgetLineUseCase implements UseCase{

    @Override
    protected BudgetLineFrequency getBudgetLineFrequency(double budget) {
        return new BudgetLineMonthlyFrequency (budget);
    }
}
