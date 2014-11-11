/**
 * Created by dino on 11/11/14.
 */
public class Category {
    private Integer id;
    private String categoryName;

    private Category(Builder builder) {
        this.id = builder.categoryId;
        this.categoryName = builder.categoryName;
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

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer categoryId;
        private String categoryName;

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

        public Category build() {
            return new Category(this);
        }
    }
}
