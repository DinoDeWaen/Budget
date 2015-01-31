package gateway;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

/**
 * Created by dino on 07/11/14.
 */
public class BudgetDataBase implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 606993467241197433L;
	
	private static BudgetDataBase budgetDataBase = null;
	public static final File file = new File(".db.ser");
	
	private int budgetId = 0;
	private int categoryId = 0;
	private int cashFlowId = 0;
	private boolean dbChanged;

	private final Map<Integer, BudgetCenter> budgets = new HashMap<Integer, BudgetCenter>();
	private final Map<Integer, Category> categories = new HashMap<Integer, Category>();
	private final Map<Integer, MoneyCashFlow> cashFlows = new HashMap<Integer, MoneyCashFlow>();
	
	public static synchronized BudgetDataBase getDB(){
		if (budgetDataBase == null)
			budgetDataBase= new BudgetDataBase();
		return budgetDataBase;
	}

	public Integer addBudget(BudgetCenter budget) {
		dbChanged = true;
		this.budgets.put(++budgetId, budget);
		return budgetId;
	}

	public BudgetCenter getBudget(Integer id) {
		return budgets.get(id);
	}

	public Integer addCategory(Category category) {
		dbChanged = true;
		categories.put(++categoryId, category);
		return categoryId;
	}

	public Category getCategory(Integer id) {
		return categories.get(id);
	}
	public Category getCategory(String name) {
		Category cat = null;
		for (Category c : categories.values()){
			if (c.getName().equals(name))
				cat = c;
		}
		return cat;
	}

	public Integer addCashFlow(Integer parentId, MoneyCashFlow cashFlow) {
		dbChanged = true;
		cashFlows.put(++cashFlowId, cashFlow);
		BudgetCenter budget = getBudget(parentId);
		budget.addCashFlow(cashFlow);

		return cashFlowId;
	}

	public MoneyCashFlow getCashFlow(Integer id) {
		return cashFlows.get(id);
	}

	public static void save() {
		if (budgetDataBase.dbChanged)
			trySave();
	}

	private static void trySave() {
		try {
     		saveDbToFile();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	private static void saveDbToFile() throws IOException,FileNotFoundException {
		file.createNewFile();
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
			out.writeObject(budgetDataBase);			
		} 
	}

	public static void load() {
		if (file.exists())
			tryLoad();
	}

	private static void tryLoad() {
		try {
			loadDbFromFile();
		} catch (Exception e)  {
			System.out.println("db class not found");
		}
	}

	private static void loadDbFromFile() throws IOException, ClassNotFoundException {
		budgetDataBase = null;
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
			budgetDataBase = (BudgetDataBase) in.readObject();
		}
	}

	public List<Category> getCategories() {
		return  new ArrayList<Category> (categories.values());
	}
}
