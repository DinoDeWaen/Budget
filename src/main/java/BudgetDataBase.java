import java.util.HashMap;
import java.util.Map;

/**
 * Created by dino on 07/11/14.
 */
public class BudgetDataBase {
    public static BudgetDataBase budgetDataBase = new BudgetDataBase();

    private Map<Integer, BudgetLine> budgetLines = new HashMap<Integer, BudgetLine>();
    private Map<Integer, Category> categories = new HashMap<Integer, Category>();

    public void addBudgetLine (BudgetLine budgetLine){
        budgetLines.put(budgetLine.getId(), budgetLine);
    }

    public BudgetLine getBudgetLine (Integer budgetLineId){
        return budgetLines.get(budgetLineId);
    }

    public void addCategory (Category category){
        categories.put(category.getId(), category);
    }

    public Category getCategory (Integer categoryId){
        return categories.get(categoryId);
    }
}
