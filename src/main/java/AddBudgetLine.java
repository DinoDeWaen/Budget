/**
 * Created by dino on 08/11/14.
 */
public abstract class AddBudgetLine implements BudgetLineTransactions {

    protected abstract BudgetLineFrequency getBudgetLineFrequency(double budget);

    public void add(BudgetLineDTO budgetLineDTO) {
        final BudgetLine budgetLine = buildBudgetLine(budgetLineDTO);
        DataBase.dataBase.addBudgetLine(budgetLine);
    }

    private BudgetLine buildBudgetLine(BudgetLineDTO budgetLineDTO) {
        final BudgetLineFrequency frequencyBL = getBudgetLineFrequency(budgetLineDTO.getBudget());
        return BudgetLine.newBuilder()
                .withId(budgetLineDTO.getId())
                .withBudgetLineName(budgetLineDTO.getBudgetLineName())
                .withCategoryId(budgetLineDTO.getCategoryId())
                .withBudgetLineFrequency(frequencyBL)
                .build();
    }
}
