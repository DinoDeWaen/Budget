package budget;


import category.Category;
import frequency.Frequency;

/**
 * Created by dino on 07/11/14.
 */
public class Budget {
    private Integer id;
    private Category category;
    private String name;
    private Frequency frequency;

    private Budget(Builder builder) {
        this.id = builder.id;
        this.category = builder.category;
        this.name = builder.name;
        this.frequency = builder.Frequency;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public Frequency getFrequency() {
        return frequency;
    }


    public Integer getId() {
        return id;
    }


    public Category getCategory() {
        return category;
    }


    public String getName() {
        return name;
    }


    public static final class Builder {
        private Integer id;
        private Category category = Category.emptyCategory;
        private String name;
        private Frequency Frequency;

        private Builder() {
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withCategory(Category category) {
            if (category != null) {
                this.category = category;
            }
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withFrequency(Frequency Frequency) {
            this.Frequency = Frequency;
            return this;
        }

        public Budget build() {
            return new Budget(this);
        }
    }
}
