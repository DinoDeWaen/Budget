/**
 * Created by dino on 08/11/14.
 */
public abstract class AddBudgetLineUseCase implements UseCase {

    protected abstract BudgetLineFrequency getBudgetLineFrequency(double budget);

    public void Execute(Request request) {
        BudgetDataBase db = BudgetDataBase.budgetDataBase;
        AddBudgetLineRequest BudgetLine = (AddBudgetLineRequest) request;
        BudgetLine budgetLine = new BudgetLine(BudgetLine.getId(), BudgetLine.getBudgetLineName(), BudgetLine.getCategoryName());
        BudgetLineFrequency frequencyBL = getBudgetLineFrequency(BudgetLine.getBudget());
        budgetLine.setBudgetLineFrequency(frequencyBL);
        db.addBudgetLine(BudgetLine.getId(), budgetLine);
    }
}
