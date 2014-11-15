import java.util.HashMap;
import java.util.Map;

/**
 * Created by dino on 07/11/14.
 */
public class BudgetDataBase {
    public static BudgetDataBase budgetDataBase = new BudgetDataBase();

    private Map<String, Budget> budgets = new HashMap<String, Budget>();
    private Map<String, Category> categories = new HashMap<String, Category >();


    public void addBudget(Budget budget){this.budgets.put(budget.getName(), budget);}

    public Budget getBudget(String budgetName){
        return budgets.get(budgetName);
    }

    public void addCategory (Category category){
        categories.put(category.getName(), category);
    }

    public Category getCategory (String categoryName){
        return categories.get(categoryName);
    }
}
