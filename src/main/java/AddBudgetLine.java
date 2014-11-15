/**
 * Created by dino on 08/11/14.
 */
public abstract class AddBudgetLine implements BudgetLineTransactions {

    protected abstract BudgetLineFrequency getBudgetLineFrequency(double budget);

    public void add(BudgetLineDTO budgetLineDTO) {
        final BudgetLine budgetLine = buildBudgetLine(budgetLineDTO);
        BudgetDataBase.budgetDataBase.addBudgetLine(budgetLine);
    }

    private BudgetLine buildBudgetLine(BudgetLineDTO budgetLineDTO) {
        final BudgetLineFrequency frequencyBL = getBudgetLineFrequency(budgetLineDTO.getBudget());
        return BudgetLine.newBuilder()
                .withId(budgetLineDTO.getId())
                .withBudgetLineName(budgetLineDTO.getBudgetLineName())
                .withCategory(budgetLineDTO.getCategory())
                .withBudgetLineFrequency(frequencyBL)
                .build();
    }
}
