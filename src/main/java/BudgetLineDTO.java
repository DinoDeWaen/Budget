/**
 * Created by dino on 07/11/14.
 */
public class BudgetLineDTO {
    private Category category;
    private String budgetLineName;
    private double budget;
    //TODO Je Id meegeven vanit je request is wel erg raar/gevaarlijk
    private Integer id;

    private BudgetLineDTO(Builder builder) {
        this.category = builder.category;
        this.budgetLineName = builder.budgetLineName;
        this.budget = builder.budget;
        this.id = builder.id;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Category getCategory() {
        return category;
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
        private Category category;
        private String budgetLineName;
        private double budget;
        private Integer id;

        private Builder() {
        }

        public Builder withCategory(Category category) {
            this.category = category;
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

        public BudgetLineDTO build() {
            return new BudgetLineDTO(this);
        }


    }
}
