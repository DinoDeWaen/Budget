package gateway;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import budget.BudgetCenter;
import cashFlow.MoneyCashFlow;
import category.Category;
import category.CategoryDTO;

/**
 * Created by dino on 07/11/14.
 */
public class BudgetDataBase implements java.io.Serializable{
	public static BudgetDataBase budgetDataBase = new BudgetDataBase();
	public static final File file = new File("c://temp/db.ser");
	private static Integer budgetId = 0;
	private static Integer categoryId = 0;
	private static Integer cashFlowId = 0;

	private final Map<Integer, BudgetCenter> budgets = new HashMap<Integer, BudgetCenter>();
	private final Map<Integer, Category> categories = new HashMap<Integer, Category>();
	private final Map<Integer, MoneyCashFlow> cashFlows = new HashMap<Integer, MoneyCashFlow>();

	public Integer addBudget(BudgetCenter budget) {
		this.budgets.put(++budgetId, budget);
		return budgetId;
	}

	public BudgetCenter getBudget(Integer id) {
		return budgets.get(id);
	}

	public Integer addCategory(Category category) {

		categories.put(++categoryId, category);
		return categoryId;
	}

	public Category getCategory(Integer id) {
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

	public static void save() {
		try {
     		file.createNewFile();
				
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(budgetDataBase);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in " + file.getAbsolutePath());
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static void load() {
		budgetDataBase = null;
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			budgetDataBase = (BudgetDataBase) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("db class not found");
			c.printStackTrace();
			return;
		}
	}

	public List<Category> getCategories() {
		return  new ArrayList<Category> (categories.values());
	}
}
