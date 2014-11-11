/**
 * Created by dino on 11/11/14.
 */
public class AddCategoryRequest extends Request {
    private Integer categoryId;
    private String categoryName;

    private AddCategoryRequest(Builder builder) {
        setCategoryId(builder.categoryId);
        setCategoryName(builder.categoryName);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

        public AddCategoryRequest build() {
            return new AddCategoryRequest(this);
        }
    }
}
