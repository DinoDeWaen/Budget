/**
 * Created by dino on 07/11/14.
 */
public class AddBudgetLineRequest extends Request {
    private String categoryName;
    private String budgetLineName;
    private double budget;
    private Integer id;

    private AddBudgetLineRequest(Builder builder) {
        this.categoryName = builder.categoryName;
        this.budgetLineName = builder.budgetLineName;
        this.budget = builder.budget;
        this.id = builder.id;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getCategoryName() {
        return categoryName;
    }


    public String getBudgetLineName() {
        return budgetLineName;
    }


    public double getBudget() {
        return budget;
    }


    public Integer getId() {
        return id;
    }


    public static final class Builder {
        private String categoryName;
        private String budgetLineName;
        private double budget;
        private Integer id;

        private Builder() {
        }

        public Builder withCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public Builder withBudgetLineName(String budgetLineName) {
            this.budgetLineName = budgetLineName;
            return this;
        }

        public Builder withBudget(double budget) {
            this.budget = budget;
            return this;
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public AddBudgetLineRequest build() {
            return new AddBudgetLineRequest(this);
        }
    }
}
