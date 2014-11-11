/**
 * Created by dino on 08/11/14.
 */
public abstract class AddBudgetLineUseCase implements UseCase {

    protected abstract BudgetLineFrequency getBudgetLineFrequency(double budget);

    public void execute(Request request) {
        AddBudgetLineRequest addBudgetLineRequest = (AddBudgetLineRequest) request;
        final BudgetLine budgetLine = buildBudgetLine(addBudgetLineRequest);
        BudgetDataBase.budgetDataBase.addBudgetLine(budgetLine);
    }

    private BudgetLine buildBudgetLine(AddBudgetLineRequest addBudgetLineRequest) {
        final BudgetLineFrequency frequencyBL = getBudgetLineFrequency(addBudgetLineRequest.getBudget());
        return BudgetLine.newBuilder()
                .withId(addBudgetLineRequest.getId())
                .withBudgetLineName(addBudgetLineRequest.getBudgetLineName())
                .withCategoryId(addBudgetLineRequest.getCategoryId())
                .withBudgetLineFrequency(frequencyBL)
                .build();
    }
}
