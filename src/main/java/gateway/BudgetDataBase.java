package gateway;
import java.util.HashMap;
import java.util.Map;

import budget.Budget;
import category.Category;

/**
 * Created by dino on 07/11/14.
 */
public class BudgetDataBase {
    public static BudgetDataBase budgetDataBase = new BudgetDataBase();
    private static Integer budgetId = 0;
    private static Integer categoryId = 0;

    private Map<Integer, Budget> budgets = new HashMap<Integer, Budget>();
    private Map<Integer, Category> categories = new HashMap<Integer, Category >();


    public Integer addBudget(Budget budget){
    	this.budgets.put(++budgetId, budget);
    	return budgetId;
    	}

    public Budget getBudget(Integer id){
        return budgets.get(id);
    }    

    public Integer addCategory (Category category){
    	
        categories.put(++categoryId, category);
        return categoryId;
    }

    public Category getCategory (Integer id){
        return categories.get(id);
    }
}
