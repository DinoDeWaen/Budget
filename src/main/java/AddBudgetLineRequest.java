/**
 * Created by dino on 07/11/14.
 */
public class AddBudgetLineRequest extends Request {
    private String categoryName;
    private String budgetLineName;
    private double budget;
    private Integer id;

    public AddBudgetLineRequest(Integer id, String categoryName, String budgetLineName, double budget) {
        this.id = id;
        this.setCategoryName(categoryName);
        this.setBudgetLineName(budgetLineName);
        this.setBudget(budget);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBudgetLineName() {
        return budgetLineName;
    }

    public void setBudgetLineName(String budgetLineName) {
        this.budgetLineName = budgetLineName;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
