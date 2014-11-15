/**
 * Created by dino on 07/11/14.
 */
public class BudgetDTO {
    private Category category;
    private String name;
    private double amount;

    private BudgetDTO(Builder builder) {
        this.category = builder.category;
        this.name = builder.name;
        this.amount = builder.amount;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Category getCategory() {
        return category;
    }


    public String getName() {
        return name;
    }


    public double getAmount() {
        return amount;
    }


    public static final class Builder {
        private Category category;
        private String name;
        private double amount;

        private Builder() {
        }

        public Builder withCategory(Category category) {
            this.category = category;
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
