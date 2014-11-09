import java.util.HashMap;
import java.util.Map;

/**
 * Created by dino on 07/11/14.
 */
public class BudgetDataBase {
    public static BudgetDataBase budgetDataBase = new BudgetDataBase();

    private Map<Integer, BudgetLine> budgetLines = new HashMap<Integer, BudgetLine>();

    public void addBudgetLine (int id, BudgetLine budgetLine){
        budgetLines.put(id, budgetLine);
    }

    public BudgetLine getBudgetLine (int BudgetLineId){
        return budgetLines.get(BudgetLineId);
    }
}
