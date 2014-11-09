/**
 * Created by dino on 07/11/14.
 */
public class AddBudgetLineBuilder implements RequestBuilder {
    @Override
    public Request createAddBudgetLineRequest(int id, String categoryName, String budgetLineName, double budget) {
        return new AddBudgetLineRequest(id, categoryName, budgetLineName, budget);
    }
}
