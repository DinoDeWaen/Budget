package gateway;
import java.util.HashMap;
import java.util.Map;

import budget.BudgetCenter;
import cashFlow.MoneyCashFlow;
import category.Category;

/**
 * Created by dino on 07/11/14.
 */
public class BudgetDataBase {
    public static BudgetDataBase budgetDataBase = new BudgetDataBase();
    private static Integer budgetId = 0;
    private static Integer categoryId = 0;
    private static Integer cashFlowId = 0;

    private Map<Integer, BudgetCenter> budgets = new HashMap<Integer, BudgetCenter>();
    private Map<Integer, Category> categories = new HashMap<Integer, Category >();
    private Map<Integer, MoneyCashFlow> cashFlows = new HashMap<Integer, MoneyCashFlow >();


    public Integer addBudget(BudgetCenter budget){
    	this.budgets.put(++budgetId, budget);
    	return budgetId;
    	}

    public BudgetCenter getBudget(Integer id){
        return budgets.get(id);
    }    

    public Integer addCategory (Category category){
    	
        categories.put(++categoryId, category);
        return categoryId;
    }

    public Category getCategory (Integer id){
        return categories.get(id);
    }

	public Integer addCashFlow(Integer parentId, MoneyCashFlow cashFlow) {
		cashFlows.put(++cashFlowId, cashFlow);
		BudgetCenter budget = getBudget(parentId);
		budget.addCashFlow(cashFlow);
		
		return cashFlowId;
	}

	public MoneyCashFlow getCashFlow(Integer id) {
		return cashFlows.get(id);
	}
}
