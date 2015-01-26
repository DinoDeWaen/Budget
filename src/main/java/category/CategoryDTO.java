package category;
/**
 * Created by dino on 11/11/14.
 */
public class CategoryDTO {
    private final String name;

    public String getName() {
		return name;
	}

	private CategoryDTO(Builder builder) {
        this.name = builder.name;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String name;

        private Builder() {
        }

        public Builder withCategoryName(String name) {
            this.name = name;
            return this;
        }

        public CategoryDTO build() {
            return new CategoryDTO(this);
        }
    }
}
