package category;
/**
 * Created by dino on 11/11/14.
 */
public class Category {
    private Integer id;
    private String name;

    public static final Category emptyCategory = Category.newBuilder().withCategoryName("Not assigned").withCategoryId(1).build();

    private Category(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private String name;

        private Builder() {
        }

        public Builder withCategoryId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withCategoryName(String name) {
            this.name = name;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
