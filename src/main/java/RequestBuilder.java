/**
 * Created by dino on 07/11/14.
 */
public interface RequestBuilder {
    Request createAddBudgetLineRequest(int id, String categoryName, String budgetLineName, double budget);
}
