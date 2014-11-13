import java.util.HashMap;
import java.util.Map;

/**
 * Created by dino on 11/11/14.
 */
public class Category {
    private Integer id;
    private String categoryName;
    private Map<Integer, BudgetLine> budgetLines = new HashMap<Integer, BudgetLine>();

    private Category(Builder builder) {
        this.id = builder.categoryId;
        this.categoryName = builder.categoryName;
        this.budgetLines = builder.budgetLines;
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

    public void addBudgetLine (BudgetLine budgetLine){
        this.budgetLines.put(budgetLine.getId(), budgetLine);
    }

    public BudgetLine getBudgetLine (Integer id){
        return this.budgetLines.get(id);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer categoryId;
        private String categoryName;
        private Map<Integer, BudgetLine> budgetLines = new HashMap<Integer, BudgetLine>();

        private Builder() {
        }

        public Builder withCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder withCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public Builder withBudgetLines(Map<Integer, BudgetLine> budgetLines) {
            this.budgetLines = budgetLines;
            return this;
        }
        public Category build() {
            return new Category(this);
        }
    }
}
