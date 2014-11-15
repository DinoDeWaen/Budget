import java.util.HashMap;
import java.util.Map;

/**
 * Created by dino on 07/11/14.
 */
public class BudgetDataBase {
    public static BudgetDataBase budgetDataBase = new BudgetDataBase();

    private Map<Integer, Budget> budget = new HashMap<Integer, Budget>();
    private Map<Integer, Category> categories = new HashMap<Integer, Category >();

    public void addBudget(Budget budget){
        this.budget.put(budget.getId(), budget);
    }

    public Budget getBudget(Integer budgetLineId){
        return budget.get(budgetLineId);
    }

    public void addCategory (Category category){
        categories.put(category.getId(), category);
    }

    public Category getCategory (Integer categoryId){
        return categories.get(categoryId);
    }
}
