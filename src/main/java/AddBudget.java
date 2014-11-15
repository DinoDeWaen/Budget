/**
 * Created by dino on 08/11/14.
 */
public abstract class AddBudget implements BudgetTransactions {

    protected abstract Frequency getBudgetLineFrequency(double budget);

    public void addBudget(BudgetDTO budgetDTO) {
        final Budget budget = buildBudget(budgetDTO);
        BudgetDataBase.budgetDataBase.addBudget(budget);
    }

    private Budget buildBudget(BudgetDTO budgetDTO) {
        final Frequency frequencyBL = getBudgetLineFrequency(budgetDTO.getAmount());
        return Budget.newBuilder()
                .withName(budgetDTO.getName())
                .withCategory(budgetDTO.getCategory())
                .withFrequency(frequencyBL)
                .build();
    }
}
