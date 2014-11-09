/**
 * Created by dino on 07/11/14.
 */
public class BudgetLine {
    private Integer id;
    private String categoryName;
    private String budgetLineName;

    private BudgetLineFrequency BudgetLineFrequency;


    public BudgetLine(Integer id,String budgetLineName, String categoryName) {
        this.setId(id);
        this.setCategoryName(categoryName);
        this.setBudgetLineName(budgetLineName);
    }

    public BudgetLineFrequency getBudgetLineFrequency() {
        return BudgetLineFrequency;
    }

    public void setBudgetLineFrequency(BudgetLineFrequency budgetLineFrequency) {
        BudgetLineFrequency = budgetLineFrequency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
