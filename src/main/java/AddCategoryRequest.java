/**
 * Created by dino on 11/11/14.
 */
public class AddCategoryRequest extends Request {
    private int categoryId;
    private String categoryName;

    private AddCategoryRequest(Builder builder) {
        categoryId = builder.categoryId;
        categoryName = builder.categoryName;
    }

    public static final class Builder {
        private int categoryId;
        private String categoryName;

        public Builder() {
        }

        public Builder withCategoryId(int categoryId) {
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
