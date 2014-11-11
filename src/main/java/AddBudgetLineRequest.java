/**
 * Created by dino on 07/11/14.
 */
public class AddBudgetLineRequest extends Request {
    private Integer categoryId;
    private String budgetLineName;
    private double budget;
    //TODO Je Id meegeven vanit je request is wel erg raar/gevaarlijk
    private Integer id;

    private AddBudgetLineRequest(Builder builder) {
        this.categoryId = builder.categoryId;
        this.budgetLineName = builder.budgetLineName;
        this.budget = builder.budget;
        this.id = builder.id;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getCategoryId() {
        return categoryId;
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
        private Integer categoryId;
        private String budgetLineName;
        private double budget;
        private Integer id;

        private Builder() {
        }

        public Builder withCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
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
