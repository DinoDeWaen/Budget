/**
 * Created by dino on 07/11/14.
 */
public class BudgetLine {
    private Integer id;
    private Category category;
    private String budgetLineName;
    private BudgetLineFrequency budgetLineFrequency;

    private BudgetLine(Builder builder) {
        this.id = builder.id;
        this.category = builder.category;
        this.budgetLineName = builder.budgetLineName;
        this.budgetLineFrequency = builder.BudgetLineFrequency;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public BudgetLineFrequency getBudgetLineFrequency() {
        return budgetLineFrequency;
    }


    public Integer getId() {
        return id;
    }


    public Category getCategory() {
        return category;
    }


    public String getBudgetLineName() {
        return budgetLineName;
    }


    public static final class Builder {
        private Integer id;
        private Category category;
        private String budgetLineName;
        private BudgetLineFrequency BudgetLineFrequency;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder withBudgetLineName(String budgetLineName) {
            this.budgetLineName = budgetLineName;
            return this;
        }

        public Builder withBudgetLineFrequency(BudgetLineFrequency BudgetLineFrequency) {
            this.BudgetLineFrequency = BudgetLineFrequency;
            return this;
        }

        public BudgetLine build() {
            return new BudgetLine(this);
        }
    }
}
