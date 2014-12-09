package budget;

/**
 * Created by dino on 07/11/14.
 */
public class BudgetDTO {
    private Integer categoryId;
    private String name;
    private double amount;

    private BudgetDTO(Builder builder) {
        this.categoryId = builder.categoryId;
        this.name = builder.name;
        this.amount = builder.amount;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getCategoryId() {
        return categoryId;
    }


    public String getName() {
        return name;
    }


    public double getAmount() {
        return amount;
    }


    public static final class Builder {
        private Integer categoryId;
        private String name;
        private double amount;

        private Builder() {
        }

        public Builder withCategory(Integer categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public BudgetDTO build() {
            return new BudgetDTO(this);
        }


    }
}
